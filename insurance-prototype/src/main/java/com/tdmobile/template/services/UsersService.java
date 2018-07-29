package com.tdmobile.template.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdmobile.template.dao.RolesDao;
import com.tdmobile.template.dao.UsersDao;
import com.tdmobile.template.dao.UsersRolDao;
import com.tdmobile.template.entity.Users;
import com.tdmobile.template.entity.UsersRol;
import com.tdmobile.template.entity.UsersStatus;
import com.tdmobile.template.exceptions.UsersExceptions;
import com.tdmobile.template.model.users.Create;
import com.tdmobile.template.model.users.Login;
import com.tdmobile.template.model.users.UserView;
import com.tdmobile.template.services.security.UtilsSecurity;

@Service
public class UsersService {

    @Autowired
    private UsersDao userDao;

    @Autowired
    private ClientsServices clientsServices;

    @Autowired
    private PoliciesServices policiesServices;

    @Autowired
    private RolesDao rolesDao;

    @Autowired
    private UsersRolDao usersRolDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<Users> findAll() {
	return userDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Users> findUser(String user) {
	return userDao.findfield("User", user);
    }

    @Transactional(readOnly = true)
    public UserView findUserApi(String user) {
	List<Users> userFind = userDao.findfield("User", user);
	UserView view = new UserView();
	if (userFind != null && userFind.size() > 0) {
	    if (userFind.get(0).getUserRoleList().get(0).getIdRoles().getRole().equals("ROLE_CLIENT")) {
		userFind.get(0).setDataClients(clientsServices.findForUser(userFind.get(0).getIdUser()));
		userFind.get(0).setPolicies(policiesServices.findForUser(userFind.get(0).getIdUser()));
	    }
	    ModelMapper modelMapper = new ModelMapper();
	    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	    view = modelMapper.map(userFind.get(0), UserView.class);
	    view.setRole(userFind.get(0).getUserRoleList().get(0).getIdRoles().getRole());
	}
	return view;
    }

    @Transactional(readOnly = true)
    public Create findId(Integer idUser) {
	Users user = userDao.findId(idUser);
	ModelMapper modelMapper = new ModelMapper();
	Create userFind = modelMapper.map(user, Create.class);
	List<Integer> rol = new ArrayList<Integer>();
	user.getUserRoleList().forEach((userRol) -> {
	    rol.add(userRol.getIdRoles().getIdRoles());
	});
	userFind.setUserRoles(rol);
	return userFind;
    }

    @Transactional
    public void insert(Create userCreate) {
	ModelMapper modelMapper = new ModelMapper();
	Users user = modelMapper.map(userCreate, Users.class);
	user.setIdUsersStatus(new UsersStatus(1L));
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	List<UsersRol> roles = new ArrayList<UsersRol>();
	for (Integer idrol : userCreate.getUserRoles()) {
	    UsersRol ur = new UsersRol();
	    ur.setIdRoles(rolesDao.findId(idrol));
	    ur.setIdUser(user);
	    roles.add(ur);
	}
	user.setUserRoleList(roles);
	userDao.persist(user);
    }

    @Transactional
    public void edit(Create userCreate) {
	ModelMapper modelMapper = new ModelMapper();
	Users user = modelMapper.map(userCreate, Users.class);
	if (user.getPassword() == null || user.getPassword().equals("")) {
	    Users original = userDao.findId(userCreate.getIdUser());
	    user.setPassword(original.getPassword());
	}
	List<UsersRol> roles = new ArrayList<UsersRol>();
	userCreate.getUserRoles().forEach((idrol) -> {
	    usersRolDao.findfield("idUser", user).forEach((urold) -> {
		usersRolDao.remove(urold);
	    });
	    UsersRol ur = new UsersRol();
	    ur.setIdRoles(rolesDao.findId(idrol));
	    ur.setIdUser(user);
	    roles.add(ur);
	});
	user.setUserRoleList(roles);
	userDao.merge(user);
    }

    @Transactional
    public String loginApi(Login login) throws UsersExceptions {
	List<Users> lstUser = userDao.findfield("User", login.getUser());
	Users user = lstUser.size() > 0 ? lstUser.get(0) : null;
	String token = null;
	if (user != null && passwordEncoder.matches(login.getPassword(), user.getPassword())) {
	    token = UtilsSecurity.secureToken();
	    user.setToken(token);
	    userDao.merge(user);
	} else {
	    throw new UsersExceptions("text.error.userpassword");
	}
	return token;
    }

    @Transactional
    public void logoutApi(String token) throws UsersExceptions {
	List<Users> lstUser = userDao.findfield("Token", token);
	Users user = lstUser.size() > 0 ? lstUser.get(0) : null;
	if (user != null) {
	    user.setToken(null);
	    userDao.merge(user);
	} else {
	    throw new UsersExceptions("text.error.usertoken");
	}
    }
}
