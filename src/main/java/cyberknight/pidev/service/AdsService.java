package cyberknight.pidev.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import cyberknight.pidev.exception.TokenException;
import cyberknight.pidev.model.Ads;
import cyberknight.pidev.model.user;
import cyberknight.pidev.repository.AdsRepository;
import cyberknight.pidev.repository.userRepository;



@Service
public class AdsService {
	@Autowired
	AdsRepository<Ads> AdsRepository;
	@Autowired
	userRepository userRepository;
	

	@Transactional
	public List<Ads> getAllAds() {
		return (List<Ads>) AdsRepository.findAll();
	}

	@Transactional
	public boolean addAds(Ads ads) {
//		String username = "";
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if (principal instanceof UserDetails) {
//			username = ((UserDetails) principal).getUsername();
//			//System.out.println(username);
//		} else {
//			username = principal.toString();
//			//System.out.println(username);
//		}
//		//System.out.println(ads.getUser());
//		Optional<user> opUser = userRepository.findByUsername(username);
//		//System.out.println(username);
//		opUser.orElseThrow(() -> new TokenException("User not found"));
//		user user = new user();
//		user = opUser.get();
//		ads.setUser(user);
		ads.setUser(null);
		return AdsRepository.save(ads) != null;
	}

	@Transactional
	public boolean updateAds(Ads ads) {
//		String username = "";
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if (principal instanceof UserDetails) {
//			username = ((UserDetails) principal).getUsername();
//		} else {
//			username = principal.toString();
//		}
//		Optional<user> opUser = userRepository.findByUsername(username);
//		opUser.orElseThrow(() -> new TokenException("User not found"));
//		user user = new user();
//		user = opUser.get();
//		ads.setUser(user);
		ads.setUser(null);
		return AdsRepository.save(ads) != null;
	}

	@Transactional
	public boolean deleteAds(Long Adsid) {
		AdsRepository.deleteById(Adsid);
		return !AdsRepository.existsById(Adsid);

	}
	
	@Transactional
	public boolean AcceptAds(Ads ads) throws Exception {
		Optional<Ads> Optionalproduct = AdsRepository.findById(ads.getId());
		Optionalproduct.orElseThrow(() -> new Exception("Ads not found"));
		Ads A=new Ads();
		A=Optionalproduct.get();
		A.setStatus("Accepted");
		return AdsRepository.save(A) != null;
	}
	

	@Transactional
	public boolean DeclineAds(Ads ads) throws Exception {
		Optional<Ads> Optionalproduct = AdsRepository.findById(ads.getId());
		Optionalproduct.orElseThrow(() -> new Exception("Ads not found"));
		Ads A=new Ads();
		A=Optionalproduct.get();
		A.setStatus("Declined");
		return AdsRepository.save(A) != null;
	}
	

	
	
	 public List<Ads> listAll() {
	        return AdsRepository.findAll(Sort.by("id").ascending());
	    }
	 
	
		
	
}
