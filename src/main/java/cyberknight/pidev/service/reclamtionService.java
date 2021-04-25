package cyberknight.pidev.service;

import java.security.Principal;
import java.time.Clock;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import cyberknight.pidev.dto.reclamationRequest;
import cyberknight.pidev.exception.TokenException;
import cyberknight.pidev.model.decision;
import cyberknight.pidev.model.reclamation;
import cyberknight.pidev.model.user;
import cyberknight.pidev.repository.reclamationRepository;
import cyberknight.pidev.repository.userRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class reclamtionService {
	private reclamationRepository reclamationRepository;
	private userRepository userRepository;

	public void createReclamation(reclamationRequest reclamationRequest) {
		reclamation reclamation = new reclamation();
		decision d = decision.UNTREATED;
		reclamation.setDecision(d);
		reclamation.setCreated(Clock.systemUTC().instant());
		reclamation.setDescription(reclamationRequest.getDescription());
		reclamation.setSubject(reclamationRequest.getSubject());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		Optional<user> opUser = userRepository.findByUsername(username);
		opUser.orElseThrow(() -> new TokenException("User not found"));
		user user = new user();
		user = opUser.get();
		reclamation.setUser(user);
		reclamationRepository.save(reclamation);
		System.out.println("Reclamtion Created");
	}

	public void deleteReclamation(reclamationRequest reclamationRequest) {
		Optional<reclamation> opRec = reclamationRepository.findByreclamtionId(reclamationRequest.getReclamtionId());
		opRec.orElseThrow(() -> new TokenException("Reclamation not found"));
		reclamationRepository.delete(opRec.get());
		System.out.println("Reclamation deleted");
	}

	public void editReclamation(reclamationRequest reclamationRequest) {
		Optional<reclamation> opRec = reclamationRepository.findByreclamtionId(reclamationRequest.getReclamtionId());
		opRec.orElseThrow(() -> new TokenException("Reclamation not found"));
		reclamation rec = new reclamation();
		rec = opRec.get();
		String str="";
		if (reclamationRequest.getReclamtionId()!=null)
		rec.setReclamtionId(reclamationRequest.getReclamtionId());
		if (reclamationRequest.getDescription()!=null)
		rec.setDescription(reclamationRequest.getDescription());
		if (reclamationRequest.getSubject()!=null)
		rec.setSubject(reclamationRequest.getSubject());
		reclamationRepository.save(rec);
		System.out.println("reclamation Edited");
	}

	public List<reclamation> getAllReclamations() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		Optional<user> opUser = userRepository.findByUsername(username);
		opUser.orElseThrow(() -> new TokenException("User not found"));
		user user = new user();
		user = opUser.get();
		return reclamationRepository.findAllByuser(user);
	}
	public List<reclamation> fetchBySubject(reclamationRequest reclamationRequest)
	{
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		Optional<user> opUser = userRepository.findByUsername(username);
		opUser.orElseThrow(() -> new TokenException("User not found"));
		user user = new user();
		user = opUser.get();
		return reclamationRepository.findByuserAndSubjectContaining(user,reclamationRequest.getSubject());//lazem nzid kifeh ymed ken mta3 current user DONE
		
	}

//	public reclamation getReclamation(long reclamationId) {
//		Optional<reclamation> opRec = reclamationRepository.findByreclamtionId(reclamationId);
//		opRec.orElseThrow(() -> new TokenException("Reclamation not found"));
//		return opRec.get();
//	}
//
//	public List getReclamation1(long reclamationId) {
//		
//		return reclamationRepository.findAllByuser(userId);
//	}
}
