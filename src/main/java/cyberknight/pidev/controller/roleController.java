package cyberknight.pidev.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cyberknight.pidev.dto.roleRequest;
import cyberknight.pidev.model.role;
import cyberknight.pidev.service.RoleService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class roleController {
	private final RoleService RoleService;

	@PostMapping("/addRole")
	public ResponseEntity<String> addRole(@RequestBody roleRequest roleRequest) {
		RoleService.createRole(roleRequest);
		return new ResponseEntity<>("Role Added : "+roleRequest.getRoleName(),OK);

	}

	@PostMapping("/deleteRole")
	public ResponseEntity<String> deleteRole(@RequestBody roleRequest roleRequest) {
		RoleService.deleteRole(roleRequest);
		return new ResponseEntity<>("Role Deleted",OK);

	}
	@GetMapping("/getRole")
	public List<role> getAll()
	{
		return RoleService.getallroles();
	}
	@PostMapping("/editRole")
	public ResponseEntity<String> editRole(@RequestBody roleRequest roleRequest) {
		RoleService.EditRole(roleRequest);
		return new ResponseEntity<>("Role Added : "+roleRequest.getRoleName(),OK);
	}

}
