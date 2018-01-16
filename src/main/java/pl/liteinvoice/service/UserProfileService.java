package pl.liteinvoice.service;

import org.springframework.stereotype.Service;
import pl.liteinvoice.model.UserProfile;

import java.util.List;

@Service
public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
