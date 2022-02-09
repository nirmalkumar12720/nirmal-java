package com.racsuggestionbox;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.context.annotation.Bean;

//import com.racsuggestionbox.entity.CategoryBO;
//import com.racsuggestionbox.entity.SuggestionBO;
//import com.racsuggestionbox.entity.TopicAreaBO;
//import com.racsuggestionbox.entity.TopicBO;
//import com.racsuggestionbox.repository.CategoryRepository;
//import com.racsuggestionbox.repository.SuggestionRepository;
//import com.racsuggestionbox.repository.TopicAreaRepository;
//import com.racsuggestionbox.repository.TopicRepository;


@SpringBootApplication
public class RacSuggessionBoxExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RacSuggessionBoxExerciseApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner runner() {
//		return new CommandLineRunner() {
//			
//			@Autowired
//		    private CategoryRepository categoryRepo;
//			
//			@Autowired 
//			TopicAreaRepository topicarearepo;
//			
//			@Autowired 
//			TopicRepository topicrepo;
//			
//			@Autowired
//			SuggestionRepository suggestionrepo;
//			
//			@Override
//			public void run(String... args) throws Exception {
//				
//			
//				
//				CategoryBO category1 = new CategoryBO(1,"category1",null,"kishore",null,null,0);
//				categoryRepo.save(category1);
//				
//				CategoryBO category2 = new CategoryBO(2,"category2",null,"jawahar",null,null,0);
//				categoryRepo.save(category2);
//				
//				CategoryBO category3 = new CategoryBO(3,"category3",null,"viswa",null,null,0);
//				categoryRepo.save(category3);
//		
//				
//				
//				TopicAreaBO topicarea1 = new TopicAreaBO(1,"topicarea1.1",null,null,null,null,0);
//				topicarea1.setCategories(category1);
//				topicarearepo.save(topicarea1);
//				
//				TopicAreaBO topicarea2 = new TopicAreaBO(2,"topicarea1.2",null,null,null,null,0);
//				topicarea2.setCategories(category1);
//				topicarearepo.save(topicarea2);
//				
//				TopicAreaBO topicarea3 = new TopicAreaBO(3,"topicarea1.3",null,null,null,null,0);
//				topicarea3.setCategories(category1);
//				topicarearepo.save(topicarea3);
//				
//				TopicBO topic1 = new TopicBO(1,"topic1",null,null,null,null,0);
//				topic1.setTopicArea(topicarea1);
//				topicrepo.save(topic1);
//				
//				TopicBO topic2 = new TopicBO(2,"topic2",null,null,null,null,0);
//				topic2.setTopicArea(topicarea1);
//				topicrepo.save(topic2);
//				
//				TopicBO topic3 = new TopicBO(3,"topic3",null,null,null,null,0);
//				topic3.setTopicArea(topicarea1);
//				topicrepo.save(topic3);
//				
//				
//				TopicAreaBO topicarea4 = new TopicAreaBO(4,"topicarea2.1",null,null,null,null,0);
//				topicarea4.setCategories(category2);
//				topicarearepo.save(topicarea4);
//				
//				TopicAreaBO topicarea5 = new TopicAreaBO(5,"topicarea2.2",null,null,null,null,0);
//				topicarea5.setCategories(category2);
//				topicarearepo.save(topicarea5);
//				
//				TopicAreaBO topicarea6 = new TopicAreaBO(6,"topicarea2.3",null,null,null,null,0);
//				topicarea6.setCategories(category2);
//				topicarearepo.save(topicarea6);
//				
//				
//				
//				TopicAreaBO topicarea7 = new TopicAreaBO(7,"topicarea3.1",null,null,null,null,0);
//				topicarea7.setCategories(category3);
//				
//				topicarearepo.save(topicarea7);
//				
//				TopicAreaBO topicarea8 = new TopicAreaBO(8,"topicarea3.2",null,null,null,null,0);
//				topicarea8.setCategories(category3);
//				topicarearepo.save(topicarea8);
//				
//				TopicAreaBO topicarea9 = new TopicAreaBO(9,"topicarea3.3",null ,null,null,null,0);
//				topicarea9.setCategories(category3);
//				topicarearepo.save(topicarea9);
//			
//				
//				
//				List<TopicAreaBO> topicAreas= new ArrayList<TopicAreaBO>();
//				topicAreas.add(topicarea1);
//				topicAreas.add(topicarea2);
//				topicAreas.add(topicarea3);
//				topicAreas.add(topicarea4);
//				topicAreas.add(topicarea5);
//				topicAreas.add(topicarea6);
//				topicAreas.add(topicarea7);
//				topicAreas.add(topicarea8);
//				topicAreas.add(topicarea9);
//	
//				List<TopicBO> topic= new ArrayList<TopicBO>();
//				topic.add(topic1);
//				topic.add(topic2);
//				topic.add(topic3);
//				
//				SuggestionBO suggestion1 = new SuggestionBO(1,"kishore","Raju",null,null,null,null,null,null,null,0);
//				//topicarea7.setSuggestion(Arrays.asList(suggestion1));
//				suggestionrepo.save(suggestion1);
//				
//				SuggestionBO suggestion2 = new SuggestionBO(2,"jawahar","mohan",null,null,null,null,null,null,null,0);
//				suggestionrepo.save(suggestion2);
//				
//				
//			}
//		};
//	}

}
