package cyberknight.pidev.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cyberknight.pidev.dto.roleRequest;
import cyberknight.pidev.exception.TokenException;
import cyberknight.pidev.model.role;
import cyberknight.pidev.model.user;
import cyberknight.pidev.repository.roleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {

	private roleRepository roleRepository;

	public void createRole(roleRequest roleRequest) {
		role role = new role();
		role.setRoleName(roleRequest.getRoleName());
		roleRepository.save(role);
		System.out.println("Role created");
	}

	public void deleteRole(roleRequest roleRequest) {
		Optional<role> opRole = roleRepository.findByroleName(roleRequest.getRoleName());
		opRole.orElseThrow(() -> new TokenException("Role not found"));
		roleRepository.delete(opRole.get());
		System.out.println("Role deleted");
	}

	public void EditRole(roleRequest roleRequest) {
		Optional<role> opRole = roleRepository.findByroleId(roleRequest.getRoleId());
		opRole.orElseThrow(() -> new TokenException("Role not found"));
		role role = new role();
		role=opRole.get();	
		role.setRoleId(roleRequest.getRoleId());
		if(roleRequest.getRoleName()!=null)	
		role.setRoleName(roleRequest.getRoleName());
		roleRepository.save(role);
		System.out.println("Role edited");
	}

	public roleRequest showRole(long id) {
		Optional<role> opRole = roleRepository.findByroleId(id);
		opRole.orElseThrow(() -> new TokenException("Role not found"));
		role role = new role();
		role = opRole.get();

		return new roleRequest(role.getRoleId(), role.getRoleName());
	}

	public List<role> getallroles() {

		return roleRepository.findAll();
	}
}
