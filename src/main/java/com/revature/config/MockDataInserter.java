package com.revature.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.ProductInfo;
import com.revature.models.Category;
import com.revature.models.Product;
import com.revature.repositories.CategoryRepository;
import com.revature.repositories.ProductRepository;
import com.revature.repositories.UserRepository;
import com.revature.services.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MockDataInserter implements CommandLineRunner {
    private final UserRepository userRepo;
    private final AuthService authService;
    private final CategoryRepository catRepo;
    private final ProductRepository productRepo;
    private final ObjectMapper mapper = new ObjectMapper();

    public MockDataInserter(UserRepository userRepo, AuthService authService, CategoryRepository catRepo, ProductRepository productRepo) {
        this.userRepo = userRepo;
        this.authService = authService;
        this.catRepo = catRepo;
        this.productRepo = productRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        RegisterRequest regReq = new RegisterRequest("a@gmail.com","12345","a",null);
//        User user = new User(regReq);
//        User created = userRepo.save(user);

//        authService.getToken(created); // verifies function
        String[] categories = {"Cloud","Dawn","Day","Dusk","Moon","Night","Space","Sun"};
        for (String cat: categories) {
            catRepo.save(new Category(cat));
        }
        List<Category> cats = new ArrayList<>();
        for (int i=1; i<=categories.length; i++){
            cats.add(catRepo.getById(i));
        }

        String base_url = "https://raw.githubusercontent.com/jsparks9/pics/main/images-by-category/";
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",2.95,base_url+"cloud/small/1.jpg",base_url+"cloud/medium/1.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",1.45,base_url+"cloud/small/10.jpg",base_url+"cloud/medium/10.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.95,base_url+"cloud/small/11.jpg",base_url+"cloud/medium/11.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",1.95,base_url+"cloud/small/12.jpg",base_url+"cloud/medium/12.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.95,base_url+"cloud/small/13.jpg",base_url+"cloud/medium/13.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",2.45,base_url+"cloud/small/14.jpg",base_url+"cloud/medium/14.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",2.95,base_url+"cloud/small/15.jpg",base_url+"cloud/medium/15.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",1.95,base_url+"cloud/small/16.jpg",base_url+"cloud/medium/16.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.45,base_url+"cloud/small/17.jpg",base_url+"cloud/medium/17.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.95,base_url+"cloud/small/18.jpg",base_url+"cloud/medium/18.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",1.95,base_url+"cloud/small/19.jpg",base_url+"cloud/medium/19.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.45,base_url+"cloud/small/2.jpg",base_url+"cloud/medium/2.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.45,base_url+"cloud/small/20.jpg",base_url+"cloud/medium/20.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",2.45,base_url+"cloud/small/3.jpg",base_url+"cloud/medium/3.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.45,base_url+"cloud/small/4.jpg",base_url+"cloud/medium/4.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",2.45,base_url+"cloud/small/5.jpg",base_url+"cloud/medium/5.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",2.95,base_url+"cloud/small/6.jpg",base_url+"cloud/medium/6.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.95,base_url+"cloud/small/7.jpg",base_url+"cloud/medium/7.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",1.45,base_url+"cloud/small/8.jpg",base_url+"cloud/medium/8.jpg"));
        productRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",2.95,base_url+"cloud/small/9.jpg",base_url+"cloud/medium/9.jpg"));
        productRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",1.45,base_url+"dawn/small/1.jpg",base_url+"dawn/medium/1.jpg"));
        productRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",0.95,base_url+"dawn/small/2.jpg",base_url+"dawn/medium/2.jpg"));
        productRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",0.45,base_url+"dawn/small/3.jpg",base_url+"dawn/medium/3.jpg"));
        productRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",1.95,base_url+"dawn/small/4.jpg",base_url+"dawn/medium/4.jpg"));
        productRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",0.95,base_url+"dawn/small/5.jpg",base_url+"dawn/medium/5.jpg"));
        productRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",0.45,base_url+"dawn/small/6.jpg",base_url+"dawn/medium/6.jpg"));
        productRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",1.45,base_url+"dawn/small/7.jpg",base_url+"dawn/medium/7.jpg"));
        productRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",0.45,base_url+"dawn/small/8.jpg",base_url+"dawn/medium/8.jpg"));
        productRepo.save(new Product(cats.get(1),"Amazon Rain Forest","Amazon Rain Forest",0.95,base_url+"dawn/small/Amazon%20Rain%20Forest.jpg",base_url+"dawn/medium/Amazon%20Rain%20Forest.jpg"));
        productRepo.save(new Product(cats.get(1),"australia-shoalhaven-heads","australia-shoalhaven-heads",0.95,base_url+"dawn/small/australia-shoalhaven-heads.jpg",base_url+"dawn/medium/australia-shoalhaven-heads.jpg"));
        productRepo.save(new Product(cats.get(1),"Cocoa Beach, FL, USA","Cocoa Beach, FL, USA",0.95,base_url+"dawn/small/Cocoa%20Beach%2C%20FL%2C%20USA.jpg",base_url+"dawn/medium/Cocoa%20Beach%2C%20FL%2C%20USA.jpg"));
        productRepo.save(new Product(cats.get(1),"Emerald Bay State Park, South Lake Tahoe, United States","Emerald Bay State Park, South Lake Tahoe, United States",0.45,base_url+"dawn/small/Emerald%20Bay%20State%20Park%2C%20South%20Lake%20Tahoe%2C%20United%20States.jpg",base_url+"dawn/medium/Emerald%20Bay%20State%20Park%2C%20South%20Lake%20Tahoe%2C%20United%20States.jpg"));
        productRepo.save(new Product(cats.get(1),"Luttach, Italy from balcony","Luttach, Italy from balcony",2.45,base_url+"dawn/small/Luttach%2C%20Italy%20from%20balcony.jpg",base_url+"dawn/medium/Luttach%2C%20Italy%20from%20balcony.jpg"));
        productRepo.save(new Product(cats.get(1),"morning Nepal","morning Nepal",1.45,base_url+"dawn/small/morning%20Nepal.jpg",base_url+"dawn/medium/morning%20Nepal.jpg"));
        productRepo.save(new Product(cats.get(1),"Mount Tamalpais, United States","Mount Tamalpais, United States",2.95,base_url+"dawn/small/Mount%20Tamalpais%2C%20United%20States.jpg",base_url+"dawn/medium/Mount%20Tamalpais%2C%20United%20States.jpg"));
        productRepo.save(new Product(cats.get(1),"Mt Mitchell State Park, Burnsville, United States","Mt Mitchell State Park, Burnsville, United States",0.45,base_url+"dawn/small/Mt%20Mitchell%20State%20Park%2C%20Burnsville%2C%20United%20States.jpg",base_url+"dawn/medium/Mt%20Mitchell%20State%20Park%2C%20Burnsville%2C%20United%20States.jpg"));
        productRepo.save(new Product(cats.get(1),"Pacific Northwest","Pacific Northwest",1.45,base_url+"dawn/small/Pacific%20Northwest.jpg",base_url+"dawn/medium/Pacific%20Northwest.jpg"));
        productRepo.save(new Product(cats.get(1),"San Diego, United States","San Diego, United States",0.95,base_url+"dawn/small/San%20Diego%2C%20United%20States.jpg",base_url+"dawn/medium/San%20Diego%2C%20United%20States.jpg"));
        productRepo.save(new Product(cats.get(1),"Summer time Adrasan, Turkey","Summer time Adrasan, Turkey",2.95,base_url+"dawn/small/Summer%20time%20Adrasan%2C%20Turkey.jpg",base_url+"dawn/medium/Summer%20time%20Adrasan%2C%20Turkey.jpg"));
        productRepo.save(new Product(cats.get(1),"Toluca, Mexico","Toluca, Mexico",2.45,base_url+"dawn/small/Toluca%2C%20Mexico.jpg",base_url+"dawn/medium/Toluca%2C%20Mexico.jpg"));
        productRepo.save(new Product(cats.get(2),"day picture","day picture",1.95,base_url+"day/small/1.jpg",base_url+"day/medium/1.jpg"));
        productRepo.save(new Product(cats.get(2),"day picture","day picture",1.95,base_url+"day/small/10.jpg",base_url+"day/medium/10.jpg"));
        productRepo.save(new Product(cats.get(2),"day picture","day picture",2.45,base_url+"day/small/11.jpg",base_url+"day/medium/11.jpg"));
        productRepo.save(new Product(cats.get(2),"day picture","day picture",1.95,base_url+"day/small/12.jpg",base_url+"day/medium/12.jpg"));
        productRepo.save(new Product(cats.get(2),"day picture","day picture",2.45,base_url+"day/small/2.jpg",base_url+"day/medium/2.jpg"));
        productRepo.save(new Product(cats.get(2),"day picture","day picture",2.95,base_url+"day/small/3.jpg",base_url+"day/medium/3.jpg"));
        productRepo.save(new Product(cats.get(2),"day picture","day picture",1.45,base_url+"day/small/4.jpg",base_url+"day/medium/4.jpg"));
        productRepo.save(new Product(cats.get(2),"day picture","day picture",0.45,base_url+"day/small/5.jpg",base_url+"day/medium/5.jpg"));
        productRepo.save(new Product(cats.get(2),"day picture","day picture",1.95,base_url+"day/small/6.jpg",base_url+"day/medium/6.jpg"));
        productRepo.save(new Product(cats.get(2),"day picture","day picture",0.45,base_url+"day/small/7.jpg",base_url+"day/medium/7.jpg"));
        productRepo.save(new Product(cats.get(2),"day picture","day picture",0.45,base_url+"day/small/8.jpg",base_url+"day/medium/8.jpg"));
        productRepo.save(new Product(cats.get(2),"day picture","day picture",2.95,base_url+"day/small/9.jpg",base_url+"day/medium/9.jpg"));
        productRepo.save(new Product(cats.get(2),"Almaty Region, Kazakhstan","Almaty Region, Kazakhstan",0.45,base_url+"day/small/Almaty%20Region%2C%20Kazakhstan.jpg",base_url+"day/medium/Almaty%20Region%2C%20Kazakhstan.jpg"));
        productRepo.save(new Product(cats.get(2),"Baloons","Baloons",2.45,base_url+"day/small/Baloons.jpg",base_url+"day/medium/Baloons.jpg"));
        productRepo.save(new Product(cats.get(2),"Castaic Lake, California, USA","Castaic Lake, California, USA",2.45,base_url+"day/small/Castaic%20Lake%2C%20California%2C%20USA.jpg",base_url+"day/medium/Castaic%20Lake%2C%20California%2C%20USA.jpg"));
        productRepo.save(new Product(cats.get(2),"Crissy Field, San Francisco, United States","Crissy Field, San Francisco, United States",1.45,base_url+"day/small/Crissy%20Field%2C%20San%20Francisco%2C%20United%20States.jpg",base_url+"day/medium/Crissy%20Field%2C%20San%20Francisco%2C%20United%20States.jpg"));
        productRepo.save(new Product(cats.get(2),"Gava, Spain","Gava, Spain",1.45,base_url+"day/small/Gava%2C%20Spain.jpg",base_url+"day/medium/Gava%2C%20Spain.jpg"));
        productRepo.save(new Product(cats.get(2),"Giraffe","Giraffe",2.95,base_url+"day/small/Giraffe.jpg",base_url+"day/medium/Giraffe.jpg"));
        productRepo.save(new Product(cats.get(2),"Lighthouse","Lighthouse",0.45,base_url+"day/small/Lighthouse.jpg",base_url+"day/medium/Lighthouse.jpg"));
        productRepo.save(new Product(cats.get(2),"Seaford, UK","Seaford, UK",2.95,base_url+"day/small/Seaford%2C%20UK.jpg",base_url+"day/medium/Seaford%2C%20UK.jpg"));
        productRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/1.jpg",base_url+"dusk/medium/1.jpg"));
        productRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/10.jpg",base_url+"dusk/medium/10.jpg"));
        productRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",2.45,base_url+"dusk/small/11.jpg",base_url+"dusk/medium/11.jpg"));
        productRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",0.45,base_url+"dusk/small/2.jpg",base_url+"dusk/medium/2.jpg"));
        productRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",0.95,base_url+"dusk/small/3.jpg",base_url+"dusk/medium/3.jpg"));
        productRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/4.jpg",base_url+"dusk/medium/4.jpg"));
        productRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/5.jpg",base_url+"dusk/medium/5.jpg"));
        productRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",2.45,base_url+"dusk/small/6.jpg",base_url+"dusk/medium/6.jpg"));
        productRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/7.jpg",base_url+"dusk/medium/7.jpg"));
        productRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",0.45,base_url+"dusk/small/8.jpg",base_url+"dusk/medium/8.jpg"));
        productRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",0.45,base_url+"dusk/small/9.jpg",base_url+"dusk/medium/9.jpg"));
        productRepo.save(new Product(cats.get(3),"Ankara, Türkiye","Ankara, Türkiye",1.45,base_url+"dusk/small/Ankara%2C%20Türkiye.jpg",base_url+"dusk/medium/Ankara%2C%20Türkiye.jpg"));
        productRepo.save(new Product(cats.get(3),"Countryside summer","Countryside summer",0.95,base_url+"dusk/small/Countryside%20summer.jpg",base_url+"dusk/medium/Countryside%20summer.jpg"));
        productRepo.save(new Product(cats.get(3),"Dawn dusk sunset view from hilly coastline","Dawn dusk sunset view from hilly coastline",0.45,base_url+"dusk/small/Dawn%20dusk%20sunset%20view%20from%20hilly%20coastline.jpg",base_url+"dusk/medium/Dawn%20dusk%20sunset%20view%20from%20hilly%20coastline.jpg"));
        productRepo.save(new Product(cats.get(3),"Dique de Ullum, San Juan, Argentina","Dique de Ullum, San Juan, Argentina",1.95,base_url+"dusk/small/Dique%20de%20Ullum%2C%20San%20Juan%2C%20Argentina.jpg",base_url+"dusk/medium/Dique%20de%20Ullum%2C%20San%20Juan%2C%20Argentina.jpg"));
        productRepo.save(new Product(cats.get(3),"Haaksbergen, Haaksbergen, Nederland","Haaksbergen, Haaksbergen, Nederland",1.95,base_url+"dusk/small/Haaksbergen%2C%20Haaksbergen%2C%20Nederland.jpg",base_url+"dusk/medium/Haaksbergen%2C%20Haaksbergen%2C%20Nederland.jpg"));
        productRepo.save(new Product(cats.get(3),"La Jolla, California","La Jolla, California",0.45,base_url+"dusk/small/La%20Jolla%2C%20California.jpg",base_url+"dusk/medium/La%20Jolla%2C%20California.jpg"));
        productRepo.save(new Product(cats.get(3),"Melrose, US orange sunset","Melrose, US orange sunset",2.95,base_url+"dusk/small/Melrose%2C%20US%20orange%20sunset.jpg",base_url+"dusk/medium/Melrose%2C%20US%20orange%20sunset.jpg"));
        productRepo.save(new Product(cats.get(3),"Melrose, USA","Melrose, USA",1.45,base_url+"dusk/small/Melrose%2C%20USA.jpg",base_url+"dusk/medium/Melrose%2C%20USA.jpg"));
        productRepo.save(new Product(cats.get(3),"Sestroretsk, Russia","Sestroretsk, Russia",1.95,base_url+"dusk/small/Sestroretsk%2C%20Russia.jpg",base_url+"dusk/medium/Sestroretsk%2C%20Russia.jpg"));
        productRepo.save(new Product(cats.get(3),"Shelly Beach, Coromandel, New Zealand","Shelly Beach, Coromandel, New Zealand",0.95,base_url+"dusk/small/Shelly%20Beach%2C%20Coromandel%2C%20New%20Zealand.jpg",base_url+"dusk/medium/Shelly%20Beach%2C%20Coromandel%2C%20New%20Zealand.jpg"));
        productRepo.save(new Product(cats.get(3),"Sundbyberg, Sweden, Duvbo","Sundbyberg, Sweden, Duvbo",1.45,base_url+"dusk/small/Sundbyberg%2C%20Sweden%2C%20Duvbo.jpg",base_url+"dusk/medium/Sundbyberg%2C%20Sweden%2C%20Duvbo.jpg"));
        productRepo.save(new Product(cats.get(3),"Sunset at Champion park, Austin, Texas","Sunset at Champion park, Austin, Texas",0.95,base_url+"dusk/small/Sunset%20at%20Champion%20park%2C%20Austin%2C%20Texas.jpg",base_url+"dusk/medium/Sunset%20at%20Champion%20park%2C%20Austin%2C%20Texas.jpg"));
        productRepo.save(new Product(cats.get(3),"Uluru, Petermann Northern Territory, Australia","Uluru, Petermann Northern Territory, Australia",1.45,base_url+"dusk/small/Uluru%2C%20Petermann%20Northern%20Territory%2C%20Australia.jpg",base_url+"dusk/medium/Uluru%2C%20Petermann%20Northern%20Territory%2C%20Australia.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.95,base_url+"moon/small/1.jpg",base_url+"moon/medium/1.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",1.95,base_url+"moon/small/10.jpg",base_url+"moon/medium/10.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",1.95,base_url+"moon/small/11.jpg",base_url+"moon/medium/11.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.95,base_url+"moon/small/12.jpg",base_url+"moon/medium/12.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/13.jpg",base_url+"moon/medium/13.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",1.45,base_url+"moon/small/14.jpg",base_url+"moon/medium/14.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/15.jpg",base_url+"moon/medium/15.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/16.jpg",base_url+"moon/medium/16.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.95,base_url+"moon/small/17.jpg",base_url+"moon/medium/17.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/18.jpg",base_url+"moon/medium/18.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.95,base_url+"moon/small/19.jpg",base_url+"moon/medium/19.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/2.jpg",base_url+"moon/medium/2.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.95,base_url+"moon/small/20.jpg",base_url+"moon/medium/20.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/3.jpg",base_url+"moon/medium/3.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.95,base_url+"moon/small/4.jpg",base_url+"moon/medium/4.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/5.jpg",base_url+"moon/medium/5.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/6.jpg",base_url+"moon/medium/6.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/7.jpg",base_url+"moon/medium/7.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/8.jpg",base_url+"moon/medium/8.jpg"));
        productRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/9.jpg",base_url+"moon/medium/9.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",1.95,base_url+"night/small/1.jpg",base_url+"night/medium/1.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/10.jpg",base_url+"night/medium/10.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/11.jpg",base_url+"night/medium/11.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/12.jpg",base_url+"night/medium/12.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",0.95,base_url+"night/small/13.jpg",base_url+"night/medium/13.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/14.jpg",base_url+"night/medium/14.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/15.jpg",base_url+"night/medium/15.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",0.45,base_url+"night/small/16.jpg",base_url+"night/medium/16.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/17.jpg",base_url+"night/medium/17.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",1.45,base_url+"night/small/18.jpg",base_url+"night/medium/18.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/19.jpg",base_url+"night/medium/19.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",0.95,base_url+"night/small/2.jpg",base_url+"night/medium/2.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/20.jpg",base_url+"night/medium/20.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",1.95,base_url+"night/small/3.jpg",base_url+"night/medium/3.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",1.45,base_url+"night/small/4.jpg",base_url+"night/medium/4.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/5.jpg",base_url+"night/medium/5.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",1.45,base_url+"night/small/6.jpg",base_url+"night/medium/6.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",1.95,base_url+"night/small/7.jpg",base_url+"night/medium/7.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/8.jpg",base_url+"night/medium/8.jpg"));
        productRepo.save(new Product(cats.get(5),"night picture","night picture",0.45,base_url+"night/small/9.jpg",base_url+"night/medium/9.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/1.jpg",base_url+"space/medium/1.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",0.95,base_url+"space/small/10.jpg",base_url+"space/medium/10.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",2.45,base_url+"space/small/11.jpg",base_url+"space/medium/11.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",1.95,base_url+"space/small/12.jpg",base_url+"space/medium/12.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/13.jpg",base_url+"space/medium/13.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/14.jpg",base_url+"space/medium/14.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",2.45,base_url+"space/small/15.jpg",base_url+"space/medium/15.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",0.95,base_url+"space/small/2.jpg",base_url+"space/medium/2.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",1.95,base_url+"space/small/3.jpg",base_url+"space/medium/3.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/4.jpg",base_url+"space/medium/4.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",0.45,base_url+"space/small/5.jpg",base_url+"space/medium/5.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",2.45,base_url+"space/small/6.jpg",base_url+"space/medium/6.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",2.95,base_url+"space/small/7.jpg",base_url+"space/medium/7.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/8.jpg",base_url+"space/medium/8.jpg"));
        productRepo.save(new Product(cats.get(6),"space picture","space picture",2.95,base_url+"space/small/9.jpg",base_url+"space/medium/9.jpg"));
        productRepo.save(new Product(cats.get(6),"A simulated view of Mars as it would be seen from the Mars Global Surveyor spacecraft","A simulated view of Mars as it would be seen from the Mars Global Surveyor spacecraft",0.45,base_url+"space/small/A%20simulated%20view%20of%20Mars%20as%20it%20would%20be%20seen%20from%20the%20Mars%20Global%20Surveyor%20spacecraft.jpg",base_url+"space/medium/A%20simulated%20view%20of%20Mars%20as%20it%20would%20be%20seen%20from%20the%20Mars%20Global%20Surveyor%20spacecraft.jpg"));
        productRepo.save(new Product(cats.get(6),"Jupiter","Jupiter",1.45,base_url+"space/small/Jupiter.jpg",base_url+"space/medium/Jupiter.jpg"));
        productRepo.save(new Product(cats.get(6),"Mercury","Mercury",1.45,base_url+"space/small/Mercury.jpg",base_url+"space/medium/Mercury.jpg"));
        productRepo.save(new Product(cats.get(6),"Neptune","Neptune",0.45,base_url+"space/small/Neptune.jpg",base_url+"space/medium/Neptune.jpg"));
        productRepo.save(new Product(cats.get(6),"Pluto","Pluto",1.45,base_url+"space/small/Pluto.jpg",base_url+"space/medium/Pluto.jpg"));
        productRepo.save(new Product(cats.get(6),"Saturn","Saturn",1.45,base_url+"space/small/Saturn.jpg",base_url+"space/medium/Saturn.jpg"));
        productRepo.save(new Product(cats.get(6),"Uranus as seen by Voyager 2","Uranus as seen by Voyager 2",0.95,base_url+"space/small/Uranus%20as%20seen%20by%20Voyager%202.jpg",base_url+"space/medium/Uranus%20as%20seen%20by%20Voyager%202.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",2.45,base_url+"sun/small/1.jpg",base_url+"sun/medium/1.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/10.jpg",base_url+"sun/medium/10.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/11.jpg",base_url+"sun/medium/11.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/12.jpg",base_url+"sun/medium/12.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.45,base_url+"sun/small/13.jpg",base_url+"sun/medium/13.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/14.jpg",base_url+"sun/medium/14.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.45,base_url+"sun/small/15.jpg",base_url+"sun/medium/15.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.45,base_url+"sun/small/16.jpg",base_url+"sun/medium/16.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/17.jpg",base_url+"sun/medium/17.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.45,base_url+"sun/small/18.jpg",base_url+"sun/medium/18.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/19.jpg",base_url+"sun/medium/19.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.45,base_url+"sun/small/2.jpg",base_url+"sun/medium/2.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.45,base_url+"sun/small/20.jpg",base_url+"sun/medium/20.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/3.jpg",base_url+"sun/medium/3.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",2.45,base_url+"sun/small/4.jpg",base_url+"sun/medium/4.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/5.jpg",base_url+"sun/medium/5.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/6.jpg",base_url+"sun/medium/6.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/7.jpg",base_url+"sun/medium/7.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.45,base_url+"sun/small/8.jpg",base_url+"sun/medium/8.jpg"));
        productRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/9.jpg",base_url+"sun/medium/9.jpg"));

        System.out.println("Printing Products");
        stars();
        System.out.println(mapper.writeValueAsString(productRepo.findAll().stream().map(ProductInfo::new).collect(Collectors.toList())));
        System.out.println(mapper.writeValueAsString(productRepo.findAll()));

        stars();


    }

    private static void stars() {
        System.out.println("*****************************************************************************************");
    }
}
