package com.revature.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.ProductInfo;
import com.revature.models.*;
import com.revature.repositories.*;
import com.revature.services.AuthService;
import com.revature.services.jwt.TokenService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MockDataInserter implements CommandLineRunner {

    private final AddressRepository addressRepo;
    private final CategoryRepository catRepo;
    private final OrderRepository orderRepo;
    private final OrderStatusRepository statusRepo;
    private final ProductRepository prodRepo;
    private final ProductReviewRepository reviewRepo;
    private final UserRepository userRepo;
    private final UserRoleRepository roleRepo;
    private final ObjectMapper mapper = new ObjectMapper();
    private final TokenService service;
    private final AuthService authService;


    public MockDataInserter(AddressRepository addressRepo, CategoryRepository catRepo, OrderRepository orderRepo, OrderStatusRepository statusRepo, ProductRepository prodRepo, ProductReviewRepository reviewRepo, UserRepository userRepo, UserRoleRepository roleRepo, TokenService service, AuthService authService) {
        this.addressRepo = addressRepo;
        this.catRepo = catRepo;
        this.orderRepo = orderRepo;
        this.statusRepo = statusRepo;
        this.prodRepo = prodRepo;
        this.reviewRepo = reviewRepo;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.service = service;
        this.authService = authService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Add Categories
        String[] defaultCategories = {"Cloud","Dawn","Day","Dusk","Moon","Night","Space","Sun"};
        // Alphabetical order to sync with external generation script
        for (String cat: defaultCategories) {
            catRepo.save(new Category(cat));
        }
        List<Category> cats = catRepo.findAll();

        // Add Roles
        String[] defaultRoles = {"Admin","Basic"};
        // Ordered by decreasing permission
        for (String role: defaultRoles) {
            roleRepo.save(new UserRole(role.toUpperCase()));
        }
        List<UserRole> roles = roleRepo.findAll();

        // Add Order Statuses
        String[] defaultStatuses = {"Cart","Pending","Shipped","Delivered","Canceled"};
        // Chronological order
        for (String status: defaultStatuses) {
            statusRepo.save(new OrderStatus(status));
        }
        List<OrderStatus> statuses = statusRepo.findAll();

        // Add Users
        User userAdmin  = new User("Admin","Admin","Admin@SkyView.com",
                authService.generatePassword("admin"),
                roles.get(0), // ADMIN user
                null,null);

        User userTest = new User("Tester","McTesterson","Tester1@revature.net",
                authService.generatePassword("tester"),
                roles.get(1), // BASIC user
                null,null);
//        System.out.println("Length of pass: " + userTest.getPassword().length());
        userRepo.save(userAdmin);
        userRepo.save(userTest);
        List<User> users = userRepo.findAll();

        String base_url = "https://raw.githubusercontent.com/jsparks9/pics/main/images-by-category/";
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",2.95,base_url+"cloud/small/1.jpg",base_url+"cloud/medium/1.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",1.45,base_url+"cloud/small/10.jpg",base_url+"cloud/medium/10.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.95,base_url+"cloud/small/11.jpg",base_url+"cloud/medium/11.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",1.95,base_url+"cloud/small/12.jpg",base_url+"cloud/medium/12.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.95,base_url+"cloud/small/13.jpg",base_url+"cloud/medium/13.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",2.45,base_url+"cloud/small/14.jpg",base_url+"cloud/medium/14.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",2.95,base_url+"cloud/small/15.jpg",base_url+"cloud/medium/15.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",1.95,base_url+"cloud/small/16.jpg",base_url+"cloud/medium/16.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.45,base_url+"cloud/small/17.jpg",base_url+"cloud/medium/17.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.95,base_url+"cloud/small/18.jpg",base_url+"cloud/medium/18.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",1.95,base_url+"cloud/small/19.jpg",base_url+"cloud/medium/19.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.45,base_url+"cloud/small/2.jpg",base_url+"cloud/medium/2.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.45,base_url+"cloud/small/20.jpg",base_url+"cloud/medium/20.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",2.45,base_url+"cloud/small/3.jpg",base_url+"cloud/medium/3.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.45,base_url+"cloud/small/4.jpg",base_url+"cloud/medium/4.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",2.45,base_url+"cloud/small/5.jpg",base_url+"cloud/medium/5.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",2.95,base_url+"cloud/small/6.jpg",base_url+"cloud/medium/6.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",0.95,base_url+"cloud/small/7.jpg",base_url+"cloud/medium/7.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",1.45,base_url+"cloud/small/8.jpg",base_url+"cloud/medium/8.jpg"));
        prodRepo.save(new Product(cats.get(0),"cloud picture","cloud picture",2.95,base_url+"cloud/small/9.jpg",base_url+"cloud/medium/9.jpg"));
        prodRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",1.45,base_url+"dawn/small/1.jpg",base_url+"dawn/medium/1.jpg"));
        prodRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",0.95,base_url+"dawn/small/2.jpg",base_url+"dawn/medium/2.jpg"));
        prodRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",0.45,base_url+"dawn/small/3.jpg",base_url+"dawn/medium/3.jpg"));
        prodRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",1.95,base_url+"dawn/small/4.jpg",base_url+"dawn/medium/4.jpg"));
        prodRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",0.95,base_url+"dawn/small/5.jpg",base_url+"dawn/medium/5.jpg"));
        prodRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",0.45,base_url+"dawn/small/6.jpg",base_url+"dawn/medium/6.jpg"));
        prodRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",1.45,base_url+"dawn/small/7.jpg",base_url+"dawn/medium/7.jpg"));
        prodRepo.save(new Product(cats.get(1),"dawn picture","dawn picture",0.45,base_url+"dawn/small/8.jpg",base_url+"dawn/medium/8.jpg"));
        prodRepo.save(new Product(cats.get(1),"Amazon Rain Forest","Amazon Rain Forest",0.95,base_url+"dawn/small/Amazon%20Rain%20Forest.jpg",base_url+"dawn/medium/Amazon%20Rain%20Forest.jpg"));
        prodRepo.save(new Product(cats.get(1),"australia-shoalhaven-heads","australia-shoalhaven-heads",0.95,base_url+"dawn/small/australia-shoalhaven-heads.jpg",base_url+"dawn/medium/australia-shoalhaven-heads.jpg"));
        prodRepo.save(new Product(cats.get(1),"Cocoa Beach, FL, USA","Cocoa Beach, FL, USA",0.95,base_url+"dawn/small/Cocoa%20Beach%2C%20FL%2C%20USA.jpg",base_url+"dawn/medium/Cocoa%20Beach%2C%20FL%2C%20USA.jpg"));
        prodRepo.save(new Product(cats.get(1),"Emerald Bay State Park, South Lake Tahoe, United States","Emerald Bay State Park, South Lake Tahoe, United States",0.45,base_url+"dawn/small/Emerald%20Bay%20State%20Park%2C%20South%20Lake%20Tahoe%2C%20United%20States.jpg",base_url+"dawn/medium/Emerald%20Bay%20State%20Park%2C%20South%20Lake%20Tahoe%2C%20United%20States.jpg"));
        prodRepo.save(new Product(cats.get(1),"Luttach, Italy from balcony","Luttach, Italy from balcony",2.45,base_url+"dawn/small/Luttach%2C%20Italy%20from%20balcony.jpg",base_url+"dawn/medium/Luttach%2C%20Italy%20from%20balcony.jpg"));
        prodRepo.save(new Product(cats.get(1),"morning Nepal","morning Nepal",1.45,base_url+"dawn/small/morning%20Nepal.jpg",base_url+"dawn/medium/morning%20Nepal.jpg"));
        prodRepo.save(new Product(cats.get(1),"Mount Tamalpais, United States","Mount Tamalpais, United States",2.95,base_url+"dawn/small/Mount%20Tamalpais%2C%20United%20States.jpg",base_url+"dawn/medium/Mount%20Tamalpais%2C%20United%20States.jpg"));
        prodRepo.save(new Product(cats.get(1),"Mt Mitchell State Park, Burnsville, United States","Mt Mitchell State Park, Burnsville, United States",0.45,base_url+"dawn/small/Mt%20Mitchell%20State%20Park%2C%20Burnsville%2C%20United%20States.jpg",base_url+"dawn/medium/Mt%20Mitchell%20State%20Park%2C%20Burnsville%2C%20United%20States.jpg"));
        prodRepo.save(new Product(cats.get(1),"Pacific Northwest","Pacific Northwest",1.45,base_url+"dawn/small/Pacific%20Northwest.jpg",base_url+"dawn/medium/Pacific%20Northwest.jpg"));
        prodRepo.save(new Product(cats.get(1),"San Diego, United States","San Diego, United States",0.95,base_url+"dawn/small/San%20Diego%2C%20United%20States.jpg",base_url+"dawn/medium/San%20Diego%2C%20United%20States.jpg"));
        prodRepo.save(new Product(cats.get(1),"Summer time Adrasan, Turkey","Summer time Adrasan, Turkey",2.95,base_url+"dawn/small/Summer%20time%20Adrasan%2C%20Turkey.jpg",base_url+"dawn/medium/Summer%20time%20Adrasan%2C%20Turkey.jpg"));
        prodRepo.save(new Product(cats.get(1),"Toluca, Mexico","Toluca, Mexico",2.45,base_url+"dawn/small/Toluca%2C%20Mexico.jpg",base_url+"dawn/medium/Toluca%2C%20Mexico.jpg"));
        prodRepo.save(new Product(cats.get(2),"day picture","day picture",1.95,base_url+"day/small/1.jpg",base_url+"day/medium/1.jpg"));
        prodRepo.save(new Product(cats.get(2),"day picture","day picture",1.95,base_url+"day/small/10.jpg",base_url+"day/medium/10.jpg"));
        prodRepo.save(new Product(cats.get(2),"day picture","day picture",2.45,base_url+"day/small/11.jpg",base_url+"day/medium/11.jpg"));
        prodRepo.save(new Product(cats.get(2),"day picture","day picture",1.95,base_url+"day/small/12.jpg",base_url+"day/medium/12.jpg"));
        prodRepo.save(new Product(cats.get(2),"day picture","day picture",2.45,base_url+"day/small/2.jpg",base_url+"day/medium/2.jpg"));
        prodRepo.save(new Product(cats.get(2),"day picture","day picture",2.95,base_url+"day/small/3.jpg",base_url+"day/medium/3.jpg"));
        prodRepo.save(new Product(cats.get(2),"day picture","day picture",1.45,base_url+"day/small/4.jpg",base_url+"day/medium/4.jpg"));
        prodRepo.save(new Product(cats.get(2),"day picture","day picture",0.45,base_url+"day/small/5.jpg",base_url+"day/medium/5.jpg"));
        prodRepo.save(new Product(cats.get(2),"day picture","day picture",1.95,base_url+"day/small/6.jpg",base_url+"day/medium/6.jpg"));
        prodRepo.save(new Product(cats.get(2),"day picture","day picture",0.45,base_url+"day/small/7.jpg",base_url+"day/medium/7.jpg"));
        prodRepo.save(new Product(cats.get(2),"day picture","day picture",0.45,base_url+"day/small/8.jpg",base_url+"day/medium/8.jpg"));
        prodRepo.save(new Product(cats.get(2),"day picture","day picture",2.95,base_url+"day/small/9.jpg",base_url+"day/medium/9.jpg"));
        prodRepo.save(new Product(cats.get(2),"Almaty Region, Kazakhstan","Almaty Region, Kazakhstan",0.45,base_url+"day/small/Almaty%20Region%2C%20Kazakhstan.jpg",base_url+"day/medium/Almaty%20Region%2C%20Kazakhstan.jpg"));
        prodRepo.save(new Product(cats.get(2),"Baloons","Baloons",2.45,base_url+"day/small/Baloons.jpg",base_url+"day/medium/Baloons.jpg"));
        prodRepo.save(new Product(cats.get(2),"Castaic Lake, California, USA","Castaic Lake, California, USA",2.45,base_url+"day/small/Castaic%20Lake%2C%20California%2C%20USA.jpg",base_url+"day/medium/Castaic%20Lake%2C%20California%2C%20USA.jpg"));
        prodRepo.save(new Product(cats.get(2),"Crissy Field, San Francisco, United States","Crissy Field, San Francisco, United States",1.45,base_url+"day/small/Crissy%20Field%2C%20San%20Francisco%2C%20United%20States.jpg",base_url+"day/medium/Crissy%20Field%2C%20San%20Francisco%2C%20United%20States.jpg"));
        prodRepo.save(new Product(cats.get(2),"Gava, Spain","Gava, Spain",1.45,base_url+"day/small/Gava%2C%20Spain.jpg",base_url+"day/medium/Gava%2C%20Spain.jpg"));
        prodRepo.save(new Product(cats.get(2),"Giraffe","Giraffe",2.95,base_url+"day/small/Giraffe.jpg",base_url+"day/medium/Giraffe.jpg"));
        prodRepo.save(new Product(cats.get(2),"Lighthouse","Lighthouse",0.45,base_url+"day/small/Lighthouse.jpg",base_url+"day/medium/Lighthouse.jpg"));
        prodRepo.save(new Product(cats.get(2),"Seaford, UK","Seaford, UK",2.95,base_url+"day/small/Seaford%2C%20UK.jpg",base_url+"day/medium/Seaford%2C%20UK.jpg"));
        prodRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/1.jpg",base_url+"dusk/medium/1.jpg"));
        prodRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/10.jpg",base_url+"dusk/medium/10.jpg"));
        prodRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",2.45,base_url+"dusk/small/11.jpg",base_url+"dusk/medium/11.jpg"));
        prodRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",0.45,base_url+"dusk/small/2.jpg",base_url+"dusk/medium/2.jpg"));
        prodRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",0.95,base_url+"dusk/small/3.jpg",base_url+"dusk/medium/3.jpg"));
        prodRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/4.jpg",base_url+"dusk/medium/4.jpg"));
        prodRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/5.jpg",base_url+"dusk/medium/5.jpg"));
        prodRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",2.45,base_url+"dusk/small/6.jpg",base_url+"dusk/medium/6.jpg"));
        prodRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/7.jpg",base_url+"dusk/medium/7.jpg"));
        prodRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",0.45,base_url+"dusk/small/8.jpg",base_url+"dusk/medium/8.jpg"));
        prodRepo.save(new Product(cats.get(3),"dusk picture","dusk picture",0.45,base_url+"dusk/small/9.jpg",base_url+"dusk/medium/9.jpg"));
        prodRepo.save(new Product(cats.get(3),"Ankara, Türkiye","Ankara, Türkiye",1.45,base_url+"dusk/small/Ankara%2C%20Türkiye.jpg",base_url+"dusk/medium/Ankara%2C%20Türkiye.jpg"));
        prodRepo.save(new Product(cats.get(3),"Countryside summer","Countryside summer",0.95,base_url+"dusk/small/Countryside%20summer.jpg",base_url+"dusk/medium/Countryside%20summer.jpg"));
        prodRepo.save(new Product(cats.get(3),"Dawn dusk sunset view from hilly coastline","Dawn dusk sunset view from hilly coastline",0.45,base_url+"dusk/small/Dawn%20dusk%20sunset%20view%20from%20hilly%20coastline.jpg",base_url+"dusk/medium/Dawn%20dusk%20sunset%20view%20from%20hilly%20coastline.jpg"));
        prodRepo.save(new Product(cats.get(3),"Dique de Ullum, San Juan, Argentina","Dique de Ullum, San Juan, Argentina",1.95,base_url+"dusk/small/Dique%20de%20Ullum%2C%20San%20Juan%2C%20Argentina.jpg",base_url+"dusk/medium/Dique%20de%20Ullum%2C%20San%20Juan%2C%20Argentina.jpg"));
        prodRepo.save(new Product(cats.get(3),"Haaksbergen, Haaksbergen, Nederland","Haaksbergen, Haaksbergen, Nederland",1.95,base_url+"dusk/small/Haaksbergen%2C%20Haaksbergen%2C%20Nederland.jpg",base_url+"dusk/medium/Haaksbergen%2C%20Haaksbergen%2C%20Nederland.jpg"));
        prodRepo.save(new Product(cats.get(3),"La Jolla, California","La Jolla, California",0.45,base_url+"dusk/small/La%20Jolla%2C%20California.jpg",base_url+"dusk/medium/La%20Jolla%2C%20California.jpg"));
        prodRepo.save(new Product(cats.get(3),"Melrose, US orange sunset","Melrose, US orange sunset",2.95,base_url+"dusk/small/Melrose%2C%20US%20orange%20sunset.jpg",base_url+"dusk/medium/Melrose%2C%20US%20orange%20sunset.jpg"));
        prodRepo.save(new Product(cats.get(3),"Melrose, USA","Melrose, USA",1.45,base_url+"dusk/small/Melrose%2C%20USA.jpg",base_url+"dusk/medium/Melrose%2C%20USA.jpg"));
        prodRepo.save(new Product(cats.get(3),"Sestroretsk, Russia","Sestroretsk, Russia",1.95,base_url+"dusk/small/Sestroretsk%2C%20Russia.jpg",base_url+"dusk/medium/Sestroretsk%2C%20Russia.jpg"));
        prodRepo.save(new Product(cats.get(3),"Shelly Beach, Coromandel, New Zealand","Shelly Beach, Coromandel, New Zealand",0.95,base_url+"dusk/small/Shelly%20Beach%2C%20Coromandel%2C%20New%20Zealand.jpg",base_url+"dusk/medium/Shelly%20Beach%2C%20Coromandel%2C%20New%20Zealand.jpg"));
        prodRepo.save(new Product(cats.get(3),"Sundbyberg, Sweden, Duvbo","Sundbyberg, Sweden, Duvbo",1.45,base_url+"dusk/small/Sundbyberg%2C%20Sweden%2C%20Duvbo.jpg",base_url+"dusk/medium/Sundbyberg%2C%20Sweden%2C%20Duvbo.jpg"));
        prodRepo.save(new Product(cats.get(3),"Sunset at Champion park, Austin, Texas","Sunset at Champion park, Austin, Texas",0.95,base_url+"dusk/small/Sunset%20at%20Champion%20park%2C%20Austin%2C%20Texas.jpg",base_url+"dusk/medium/Sunset%20at%20Champion%20park%2C%20Austin%2C%20Texas.jpg"));
        prodRepo.save(new Product(cats.get(3),"Uluru, Petermann Northern Territory, Australia","Uluru, Petermann Northern Territory, Australia",1.45,base_url+"dusk/small/Uluru%2C%20Petermann%20Northern%20Territory%2C%20Australia.jpg",base_url+"dusk/medium/Uluru%2C%20Petermann%20Northern%20Territory%2C%20Australia.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.95,base_url+"moon/small/1.jpg",base_url+"moon/medium/1.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",1.95,base_url+"moon/small/10.jpg",base_url+"moon/medium/10.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",1.95,base_url+"moon/small/11.jpg",base_url+"moon/medium/11.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.95,base_url+"moon/small/12.jpg",base_url+"moon/medium/12.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/13.jpg",base_url+"moon/medium/13.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",1.45,base_url+"moon/small/14.jpg",base_url+"moon/medium/14.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/15.jpg",base_url+"moon/medium/15.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/16.jpg",base_url+"moon/medium/16.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.95,base_url+"moon/small/17.jpg",base_url+"moon/medium/17.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/18.jpg",base_url+"moon/medium/18.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.95,base_url+"moon/small/19.jpg",base_url+"moon/medium/19.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/2.jpg",base_url+"moon/medium/2.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.95,base_url+"moon/small/20.jpg",base_url+"moon/medium/20.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/3.jpg",base_url+"moon/medium/3.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.95,base_url+"moon/small/4.jpg",base_url+"moon/medium/4.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/5.jpg",base_url+"moon/medium/5.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/6.jpg",base_url+"moon/medium/6.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/7.jpg",base_url+"moon/medium/7.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/8.jpg",base_url+"moon/medium/8.jpg"));
        prodRepo.save(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/9.jpg",base_url+"moon/medium/9.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",1.95,base_url+"night/small/1.jpg",base_url+"night/medium/1.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/10.jpg",base_url+"night/medium/10.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/11.jpg",base_url+"night/medium/11.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/12.jpg",base_url+"night/medium/12.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",0.95,base_url+"night/small/13.jpg",base_url+"night/medium/13.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/14.jpg",base_url+"night/medium/14.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/15.jpg",base_url+"night/medium/15.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",0.45,base_url+"night/small/16.jpg",base_url+"night/medium/16.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/17.jpg",base_url+"night/medium/17.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",1.45,base_url+"night/small/18.jpg",base_url+"night/medium/18.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/19.jpg",base_url+"night/medium/19.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",0.95,base_url+"night/small/2.jpg",base_url+"night/medium/2.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/20.jpg",base_url+"night/medium/20.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",1.95,base_url+"night/small/3.jpg",base_url+"night/medium/3.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",1.45,base_url+"night/small/4.jpg",base_url+"night/medium/4.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/5.jpg",base_url+"night/medium/5.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",1.45,base_url+"night/small/6.jpg",base_url+"night/medium/6.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",1.95,base_url+"night/small/7.jpg",base_url+"night/medium/7.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/8.jpg",base_url+"night/medium/8.jpg"));
        prodRepo.save(new Product(cats.get(5),"night picture","night picture",0.45,base_url+"night/small/9.jpg",base_url+"night/medium/9.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/1.jpg",base_url+"space/medium/1.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",0.95,base_url+"space/small/10.jpg",base_url+"space/medium/10.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",2.45,base_url+"space/small/11.jpg",base_url+"space/medium/11.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",1.95,base_url+"space/small/12.jpg",base_url+"space/medium/12.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/13.jpg",base_url+"space/medium/13.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/14.jpg",base_url+"space/medium/14.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",2.45,base_url+"space/small/15.jpg",base_url+"space/medium/15.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",0.95,base_url+"space/small/2.jpg",base_url+"space/medium/2.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",1.95,base_url+"space/small/3.jpg",base_url+"space/medium/3.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/4.jpg",base_url+"space/medium/4.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",0.45,base_url+"space/small/5.jpg",base_url+"space/medium/5.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",2.45,base_url+"space/small/6.jpg",base_url+"space/medium/6.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",2.95,base_url+"space/small/7.jpg",base_url+"space/medium/7.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/8.jpg",base_url+"space/medium/8.jpg"));
        prodRepo.save(new Product(cats.get(6),"space picture","space picture",2.95,base_url+"space/small/9.jpg",base_url+"space/medium/9.jpg"));
        prodRepo.save(new Product(cats.get(6),"A simulated view of Mars as it would be seen from the Mars Global Surveyor spacecraft","A simulated view of Mars as it would be seen from the Mars Global Surveyor spacecraft",0.45,base_url+"space/small/A%20simulated%20view%20of%20Mars%20as%20it%20would%20be%20seen%20from%20the%20Mars%20Global%20Surveyor%20spacecraft.jpg",base_url+"space/medium/A%20simulated%20view%20of%20Mars%20as%20it%20would%20be%20seen%20from%20the%20Mars%20Global%20Surveyor%20spacecraft.jpg"));
        prodRepo.save(new Product(cats.get(6),"Jupiter","Jupiter",1.45,base_url+"space/small/Jupiter.jpg",base_url+"space/medium/Jupiter.jpg"));
        prodRepo.save(new Product(cats.get(6),"Mercury","Mercury",1.45,base_url+"space/small/Mercury.jpg",base_url+"space/medium/Mercury.jpg"));
        prodRepo.save(new Product(cats.get(6),"Neptune","Neptune",0.45,base_url+"space/small/Neptune.jpg",base_url+"space/medium/Neptune.jpg"));
        prodRepo.save(new Product(cats.get(6),"Pluto","Pluto",1.45,base_url+"space/small/Pluto.jpg",base_url+"space/medium/Pluto.jpg"));
        prodRepo.save(new Product(cats.get(6),"Saturn","Saturn",1.45,base_url+"space/small/Saturn.jpg",base_url+"space/medium/Saturn.jpg"));
        prodRepo.save(new Product(cats.get(6),"Uranus as seen by Voyager 2","Uranus as seen by Voyager 2",0.95,base_url+"space/small/Uranus%20as%20seen%20by%20Voyager%202.jpg",base_url+"space/medium/Uranus%20as%20seen%20by%20Voyager%202.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",2.45,base_url+"sun/small/1.jpg",base_url+"sun/medium/1.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/10.jpg",base_url+"sun/medium/10.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/11.jpg",base_url+"sun/medium/11.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/12.jpg",base_url+"sun/medium/12.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.45,base_url+"sun/small/13.jpg",base_url+"sun/medium/13.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/14.jpg",base_url+"sun/medium/14.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.45,base_url+"sun/small/15.jpg",base_url+"sun/medium/15.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.45,base_url+"sun/small/16.jpg",base_url+"sun/medium/16.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/17.jpg",base_url+"sun/medium/17.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.45,base_url+"sun/small/18.jpg",base_url+"sun/medium/18.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/19.jpg",base_url+"sun/medium/19.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.45,base_url+"sun/small/2.jpg",base_url+"sun/medium/2.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.45,base_url+"sun/small/20.jpg",base_url+"sun/medium/20.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/3.jpg",base_url+"sun/medium/3.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",2.45,base_url+"sun/small/4.jpg",base_url+"sun/medium/4.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/5.jpg",base_url+"sun/medium/5.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/6.jpg",base_url+"sun/medium/6.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/7.jpg",base_url+"sun/medium/7.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.45,base_url+"sun/small/8.jpg",base_url+"sun/medium/8.jpg"));
        prodRepo.save(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/9.jpg",base_url+"sun/medium/9.jpg"));

        List<Product> products = prodRepo.findAll();
        System.out.println("Printing Products");
        stars();
        System.out.println(mapper.writeValueAsString(prodRepo.findAll().stream().map(ProductInfo::new).collect(Collectors.toList())));
        System.out.println(mapper.writeValueAsString(prodRepo.findAll()));

        stars();


        // TODO : other tables; test table relationships
        // Add Product Reviews
        List<String> reviewText = new ArrayList<>();
        reviewText.add("talk about surprise!!!\n" +
                "this Clouds is snowy.\n" +
                "I tried to nab it but got biscuit all over it.\n" +
                "one of my hobbies is baking. and when i'm baking this works great.\n" +
                "This Clouds works excessively well. It speedily improves my baseball by a lot.\n" +
                "I tried to manhandle it but got bun all over it.\n" +
                "i use it daily when i'm in my courthouse.\n" +
                "I saw one of these in Canada and I bought one.\n" +
                "My co-worker Archer has one of these. He says it looks crooked.\n" +
                "this Clouds is vertical.\n" +
                "talk about sadness!\n" +
                "heard about this on original pilipino music radio, decided to give it a try.\n" +
                "It only works when I'm Rwanda.\n" +
                "this Clouds is dominant.\n" +
                "one of my hobbies is cooking. and when i'm cooking this works great.\n" +
                "The box this comes in is 5 kilometer by 6 meter and weights 20 ounce!\n" +
                "this Clouds is ghetto.\n" +
                "one of my hobbies is spearfishing. and when i'm spearfishing this works great.\n" +
                "The box this comes in is 3 kilometer by 5 foot and weights 16 megaton!!!\n" +
                "talk about contempt!\n" +
                "I tried to behead it but got truffle all over it.\n" +
                "heard about this on rebetiko radio, decided to give it a try.\n" +
                "I saw one of these in Macau and I bought one.\n" +
                "This Clouds works certainly well. It energetically improves my golf by a lot.\n" +
                "talk about sadness!!\n" +
                "I tried to kidnap it but got apricot all over it.\n" +
                "I tried to attack it but got meatball all over it.\n" +
                "this Clouds is tasty.\n" +
                "this Clouds is brown.\n" +
                "My velociraptor loves to play with it.\n" +
                "I saw one of these in Sao Tome and Principe and I bought one.\n" +
                "this Clouds is amiable.\n" +
                "It only works when I'm South Korea.\n" +
                "this Clouds is ghetto.\n" +
                "one of my hobbies is drawing. and when i'm drawing this works great.\n" +
                "My dog loves to play with it.\n" +
                "The box this comes in is 5 light-year by 6 foot and weights 17 megaton!!!\n" +
                "I tried to nail it but got strawberry all over it.\n" +
                "My neighbor Krista has one of these. She works as a salesman and she says it looks soapy.\n" +
                "heard about this on Kansas City jazz radio, decided to give it a try.\n" +
                "heard about this on timba radio, decided to give it a try.\n" +
                "I tried to manhandle it but got bun all over it.\n" +
                "this Clouds is brown.\n" +
                "heard about this on balearic beat radio, decided to give it a try.\n" +
                "I tried to slay it but got truffle all over it.\n" +
                "The box this comes in is 3 centimeter by 5 kilometer and weights 13 ounce!!\n" +
                "My co-worker Ali has one of these. He says it looks towering.\n" +
                "this Clouds is tasty.\n" +
                "This Clouds works outstandingly well. It beautifully improves my basketball by a lot.\n" +
                "My co-worker Ali has one of these. He says it looks towering.");
        reviewText.add("heard about this on original pilipino music radio, decided to give it a try.\n" +
                "heard about this on rebetiko radio, decided to give it a try.\n" +
                "This Dawn works very well. It harmonically improves my tennis by a lot.\n" +
                "i use it barely when i'm in my store.\n" +
                "i use it every Tuesday when i'm in my homeless shelter.\n" +
                "one of my hobbies is cooking. and when i'm cooking this works great.\n" +
                "It only works when I'm Bolivia.\n" +
                "It only works when I'm Argentina.\n" +
                "The box this comes in is 5 foot by 6 inch and weights 17 pound!!!\n" +
                "My neighbor Lonnie has one of these. She works as a hobbit and she says it looks microscopic.\n" +
                "My co-worker Nile has one of these. He says it looks crooked.\n" +
                "This Dawn works quite well. It pointedly improves my golf by a lot.\n" +
                "It only works when I'm South Korea.\n" +
                "It only works when I'm Mauritania.\n" +
                "My co-worker Luka has one of these. He says it looks purple.\n" +
                "I tried to strangle it but got hazelnut all over it.\n" +
                "This Dawn works really well. It sympathetically improves my baseball by a lot.\n" +
                "I saw one of these in Grenada and I bought one.\n" +
                "It only works when I'm Nepal.\n" +
                "one of my hobbies is theater. and when i'm acting this works great.\n" +
                "This Dawn works outstandingly well. It beautifully improves my basketball by a lot.\n" +
                "It only works when I'm Malaysia.\n" +
                "My co-worker Mitchell has one of these. He says it looks dry.\n" +
                "I tried to kidnap it but got apricot all over it.\n" +
                "The box this comes in is 4 mile by 5 yard and weights 18 pound!!\n" +
                "i use it until further notice when i'm in my nightclub.\n" +
                "My neighbor Aldona has one of these. She works as a butler and she says it looks humongous.\n" +
                "I tried to annihilate it but got bonbon all over it.\n" +
                "this Dawn is dominant.\n" +
                "I tried to shred it but got watermelon all over it.\n" +
                "My neighbor Krista has one of these. She works as a salesman and she says it looks soapy.\n" +
                "This Dawn works too well. It buoyantly improves my football by a lot.\n" +
                "i use it every Tuesday when i'm in my pub.\n" +
                "My peacock loves to play with it.\n" +
                "one of my hobbies is web-browsing. and when i'm browsing the web this works great.\n" +
                "My neighbor Albertina has one of these. She works as a gardener and she says it looks humongous.\n" +
                "I tried to attack it but got meatball all over it.\n" +
                "i use it until further notice when i'm in my station.\n" +
                "SoCal cockroaches are unwelcome, crafty, and tenacious. This Dawn keeps them away.\n" +
                "talk about contentment!!!\n" +
                "i use it barely when i'm in my store.\n" +
                "It only works when the lights are off.\n" +
                "talk about sadness!!\n" +
                "My co-worker Erick has one of these. He says it looks fluffy.\n" +
                "talk about sadness!!\n" +
                "I tried to nail it but got strawberry all over it.\n" +
                "My raven loves to play with it.\n" +
                "It only works when I'm Samoa.\n" +
                "My neighbor Lori has one of these. She works as a taxidermist and she says it looks whopping.\n" +
                "This Dawn works so well. It delightedly improves my football by a lot.");
        reviewText.add("I tried to scratch it but got cheeseburger all over it.\n" +
                "one of my hobbies is gaming. and when i'm gaming this works great.\n" +
                "I saw one of these in Sao Tome and Principe and I bought one.\n" +
                "It only works when I'm Singapore.\n" +
                "one of my hobbies is poetry. and when i'm writing poems this works great.\n" +
                "one of my hobbies is web-browsing. and when i'm browsing the web this works great.\n" +
                "this Day picture is vertical.\n" +
                "I tried to strangle it but got hazelnut all over it.\n" +
                "My neighbor Georgine has one of these. She works as a fireman and she says it looks colorful.\n" +
                "I saw one of these in Tanzania and I bought one.\n" +
                "My co-worker Aurthur has one of these. He says it looks white.\n" +
                "It only works when I'm Bahrain.\n" +
                "talk about surprise!!!\n" +
                "talk about remorse!!!\n" +
                "this Day picture is tasty.\n" +
                "heard about this on instrumental country radio, decided to give it a try.\n" +
                "I saw one of these in Spratly Islands and I bought one.\n" +
                "talk about fury.\n" +
                "talk about pleasure.\n" +
                "I saw one of these in The Gambia and I bought one.\n" +
                "I saw one of these in New Zealand and I bought one.\n" +
                "this Day picture is honest.\n" +
                "this Day picture is vertical.\n" +
                "My tiger loves to play with it.\n" +
                "one of my hobbies is poetry. and when i'm writing poems this works great.\n" +
                "This Day picture works so well. It imperfectly improves my baseball by a lot.\n" +
                "My neighbor Betha has one of these. She works as a teacher and she says it looks wide.\n" +
                "heard about this on powerviolence radio, decided to give it a try.\n" +
                "It only works when I'm Norway.\n" +
                "My neighbor Georgine has one of these. She works as a fireman and she says it looks colorful.\n" +
                "one of my hobbies is gaming. and when i'm gaming this works great.\n" +
                "I saw one of these in Nauru and I bought one.\n" +
                "It only works when I'm New Caledonia.\n" +
                "heard about this on melodic death metal radio, decided to give it a try.\n" +
                "i use it hardly when i'm in my prison.\n" +
                "this Day picture is whole-grain.\n" +
                "This Day picture works considerably well. It recklessly improves my basketball by a lot.\n" +
                "this Day picture is whole-grain.\n" +
                "This Day picture works really well. It wildly improves my baseball by a lot.\n" +
                "talk about hatred!!!\n" +
                "talk about contentment!!!\n" +
                "The box this comes in is 3 inch by 6 centimeter and weights 15 ounce!\n" +
                "It only works when I'm Juan de Nova Island.\n" +
                "i use it until further notice when i'm in my station.\n" +
                "I saw one of these in Tanzania and I bought one.\n" +
                "The box this comes in is 3 inch by 6 centimeter and weights 15 ounce!\n" +
                "talk about contempt!!!\n" +
                "I saw one of these in Algeria and I bought one.\n" +
                "heard about this on instrumental country radio, decided to give it a try.\n" +
                "this Day picture is whole-grain.");
        reviewText.add("My neighbor Julisa has one of these. She works as a bartender and she says it looks crooked.\n" +
                "This Dusk snap works very well. It harmonically improves my tennis by a lot.\n" +
                "My co-worker Atha has one of these. He says it looks narrow.\n" +
                "This Dusk snap works certainly well. It perfectly improves my tennis by a lot.\n" +
                "I tried to pinch it but got peanut all over it.\n" +
                "one of my hobbies is piano. and when i'm playing piano this works great.\n" +
                "one of my hobbies is skateboarding. and when i'm skateboarding this works great.\n" +
                "talk about bliss!!\n" +
                "this Dusk snap is ghetto.\n" +
                "one of my hobbies is sailing. and when i'm sailing this works great.\n" +
                "i use it occasionally when i'm in my outhouse.\n" +
                "one of my hobbies is guitar. and when i'm playing guitar this works great.\n" +
                "I tried to behead it but got truffle all over it.\n" +
                "The box this comes in is 5 inch by 6 mile and weights 15 ton!!\n" +
                "I tried to behead it but got truffle all over it.\n" +
                "one of my hobbies is web-browsing. and when i'm browsing the web this works great.\n" +
                "This is a really good Dusk snap.\n" +
                "one of my hobbies is scuba diving. and when i'm scuba diving this works great.\n" +
                "i use it usually when i'm in my alley.\n" +
                "My gentoo penguin loves to play with it.\n" +
                "heard about this on instrumental country radio, decided to give it a try.\n" +
                "talk about optimism!!!\n" +
                "one of my hobbies is web-browsing. and when i'm browsing the web this works great.\n" +
                "My neighbor Ardeth has one of these. She works as a gasman and she says it looks fuzzy.\n" +
                "It only works when I'm Singapore.\n" +
                "I saw one of these in Barbados and I bought one.\n" +
                "The box this comes in is 5 kilometer by 6 yard and weights 18 gram.\n" +
                "It only works when I'm New Caledonia.\n" +
                "I tried to cremate it but got Turkish Delight all over it.\n" +
                "This Dusk snap works quite well. It romantically improves my golf by a lot.\n" +
                "heard about this on original pilipino music radio, decided to give it a try.\n" +
                "It only works when I'm South Korea.\n" +
                "It only works when I'm South Korea.\n" +
                "i use it every Tuesday when i'm in my homeless shelter.\n" +
                "I tried to grab it but got bonbon all over it.\n" +
                "this Dusk snap is gracious.\n" +
                "It only works when I'm Singapore.\n" +
                "this Dusk snap is amiable.\n" +
                "My neighbor Aldona has one of these. She works as a butler and she says it looks humongous.\n" +
                "heard about this on melodic death metal radio, decided to give it a try.\n" +
                "this Dusk snap is ghetto.\n" +
                "This Dusk snap works excessively well. It speedily improves my baseball by a lot.\n" +
                "heard about this on original pilipino music radio, decided to give it a try.\n" +
                "My co-worker Ali has one of these. He says it looks towering.\n" +
                "one of my hobbies is mushroom cultivation. and when i'm cultivating mushrooms this works great.\n" +
                "I saw one of these in Spratly Islands and I bought one.\n" +
                "My neighbor Frona has one of these. She works as a gambler and she says it looks bearded.\n" +
                "i use it centenially when i'm in my greenhouse.\n" +
                "My penguin loves to play with it.\n" +
                "this Dusk snap is honest.");
        reviewText.add("The box this comes in is 5 yard by 6 centimeter and weights 12 kilogram.\n" +
                "My neighbor Georgie has one of these. She works as a busboy and she says it looks brown.\n" +
                "My neighbor Aldona has one of these. She works as a butler and she says it looks humongous.\n" +
                "this Moon is tasty.\n" +
                "My neighbor Montserrat has one of these. She works as a circus performer and she says it looks shriveled.\n" +
                "talk about lust!!\n" +
                "This Moon works very well. It romantically improves my football by a lot.\n" +
                "My penguin loves to play with it.\n" +
                "My neighbor Ardeth has one of these. She works as a gasman and she says it looks fuzzy.\n" +
                "I tried to decapitate it but got coconut all over it.\n" +
                "heard about this on Kansas City jazz radio, decided to give it a try.\n" +
                "My co-worker Rey has one of these. He says it looks uneven.\n" +
                "I tried to annihilate it but got bonbon all over it.\n" +
                "heard about this on original pilipino music radio, decided to give it a try.\n" +
                "My velociraptor loves to play with it.\n" +
                "My neighbor Zoa has one of these. She works as a scribe and she says it looks wide.\n" +
                "My neighbor Krista has one of these. She works as a salesman and she says it looks soapy.\n" +
                "My neighbor Zoa has one of these. She works as a scribe and she says it looks wide.\n" +
                "i use it until further notice when i'm in my nightclub.\n" +
                "The box this comes in is 3 centimeter by 5 kilometer and weights 13 ounce!!\n" +
                "this Moon is light-hearted.\n" +
                "I tried to maim it but got nectarine all over it.\n" +
                "i use it every Tuesday when i'm in my pub.\n" +
                "this Moon is vertical.\n" +
                "My neighbor Germaine has one of these. She works as a salesman and she says it looks red.\n" +
                "My co-worker Cato has one of these. He says it looks sopping.\n" +
                "The box this comes in is 5 yard by 6 centimeter and weights 18 gram!!\n" +
                "My neighbor Georgie has one of these. She works as a busboy and she says it looks brown.\n" +
                "My goldfinch loves to play with it.\n" +
                "works okay.\n" +
                "I tried to maul it but got onion all over it.\n" +
                "i use it until further notice when i'm in my nightclub.\n" +
                "My neighbor Montserrat has one of these. She works as a circus performer and she says it looks shriveled.\n" +
                "My co-worker Knute has one of these. He says it looks smoky.\n" +
                "I saw one of these in Juan de Nova Island and I bought one.\n" +
                "It only works when I'm Kuwait.\n" +
                "this Moon is mellow.\n" +
                "one of my hobbies is sailing. and when i'm sailing this works great.\n" +
                "talk about bliss!!\n" +
                "My neighbor Alida has one of these. She works as a gambler and she says it looks spotless.\n" +
                "heard about this on brazilian radio, decided to give it a try.\n" +
                "My neighbor Germaine has one of these. She works as a salesman and she says it looks red.\n" +
                "I saw one of these in Macau and I bought one.\n" +
                "i use it daily when i'm in my outhouse.\n" +
                "It only works when I'm Nepal.\n" +
                "I saw one of these in The Gambia and I bought one.\n" +
                "It only works when I'm Bolivia.\n" +
                "I saw one of these in Algeria and I bought one.\n" +
                "I saw one of these in Tanzania and I bought one.\n" +
                "this Moon is honest.");
        reviewText.add("The box this comes in is 3 light-year by 5 meter and weights 10 ounce!\n" +
                "this Night pic is dominant.\n" +
                "talk about lust!!\n" +
                "I saw one of these in Spratly Islands and I bought one.\n" +
                "I tried to decapitate it but got coconut all over it.\n" +
                "one of my hobbies is mushroom cultivation. and when i'm cultivating mushrooms this works great.\n" +
                "My tiger loves to play with it.\n" +
                "I tried to belly-flop it but got Turkish Delight all over it.\n" +
                "My co-worker Kazuo has one of these. He says it looks transparent.\n" +
                "I saw one of these in Saint Lucia and I bought one.\n" +
                "It only works when I'm Norway.\n" +
                "I tried to shred it but got watermelon all over it.\n" +
                "My co-worker Linnie has one of these. He says it looks wide.\n" +
                "This Night pic works really well. It sympathetically improves my baseball by a lot.\n" +
                "one of my hobbies is guitar. and when i'm playing guitar this works great.\n" +
                "The box this comes in is 3 meter by 5 foot and weights 11 kilogram.\n" +
                "this Night pic is flirty.\n" +
                "It only works when I'm Martinique.\n" +
                "My terrier loves to play with it.\n" +
                "This Night pic works certainly well. It perfectly improves my tennis by a lot.\n" +
                "I tried to cremate it but got Turkish Delight all over it.\n" +
                "My goldfinch loves to play with it.\n" +
                "This Night pic works really well. It sympathetically improves my baseball by a lot.\n" +
                "My hummingbird loves to play with it.\n" +
                "My demon loves to play with it.\n" +
                "It only works when I'm Malaysia.\n" +
                "i use it every Tuesday when i'm in my store.\n" +
                "It only works when I'm Rwanda.\n" +
                "My neighbor Alida has one of these. She works as a gambler and she says it looks spotless.\n" +
                "I saw this on TV and wanted to give it a try.\n" +
                "I saw one of these in Bhutan and I bought one.\n" +
                "It only works when I'm Argentina.\n" +
                "The box this comes in is 5 yard by 6 centimeter and weights 12 kilogram.\n" +
                "I tried to slay it but got truffle all over it.\n" +
                "My co-worker Bryton has one of these. He says it looks ragged.\n" +
                "My neighbor Elisha has one of these. She works as a fortune teller and she says it looks floppy.\n" +
                "I saw one of these in Moldova and I bought one.\n" +
                "one of my hobbies is spearfishing. and when i'm spearfishing this works great.\n" +
                "This Night pic works quite well. It professionally improves my soccer by a lot.\n" +
                "My co-worker Mitchell has one of these. He says it looks dry.\n" +
                "talk about lust!!\n" +
                "one of my hobbies is drawing. and when i'm drawing this works great.\n" +
                "My co-worker Archer has one of these. He says it looks crooked.\n" +
                "This Night pic works considerably well. It secretly improves my basketball by a lot.\n" +
                "I saw one of these in Kazakhstan and I bought one.\n" +
                "one of my hobbies is mushroom cultivation. and when i'm cultivating mushrooms this works great.\n" +
                "talk about contempt!!!\n" +
                "heard about this on timba radio, decided to give it a try.\n" +
                "i use it never when i'm in my nightclub.\n" +
                "this Night pic is flirty.");
        reviewText.add("I tried to slay it but got truffle all over it.\n" +
                "This Space image works so well. It hungrily improves my basketball by a lot.\n" +
                "This Space image works really well. It sympathetically improves my baseball by a lot.\n" +
                "this Space image is smooth.\n" +
                "i use it on Mondays when i'm in my fort.\n" +
                "This Space image works extremely well. It wetly improves my tennis by a lot.\n" +
                "My velociraptor loves to play with it.\n" +
                "This Space image works outstandingly well. It beautifully improves my basketball by a lot.\n" +
                "I saw one of these in Barbados and I bought one.\n" +
                "I tried to scratch it but got cheeseburger all over it.\n" +
                "My ant loves to play with it.\n" +
                "one of my hobbies is sailing. and when i'm sailing this works great.\n" +
                "It only works when I'm Rwanda.\n" +
                "this Space image is hyper.\n" +
                "It only works when I'm Mauritania.\n" +
                "talk about hatred.\n" +
                "I saw one of these in Tanzania and I bought one.\n" +
                "heard about this on new jersey hip hop radio, decided to give it a try.\n" +
                "I tried to annihilate it but got bonbon all over it.\n" +
                "I tried to eat it but got sweetmeat all over it.\n" +
                "I tried to electrocute it but got sweetmeat all over it.\n" +
                "talk about anticipation!\n" +
                "It only works when I'm Alaska.\n" +
                "i use it every Tuesday when i'm in my pub.\n" +
                "I tried to nab it but got biscuit all over it.\n" +
                "It only works when I'm Argentina.\n" +
                "i use it for 10 weeks when i'm in my sauna.\n" +
                "This Space image works considerably well. It secretly improves my basketball by a lot.\n" +
                "heard about this on balearic beat radio, decided to give it a try.\n" +
                "one of my hobbies is baking. and when i'm baking this works great.\n" +
                "I tried to decapitate it but got coconut all over it.\n" +
                "The box this comes in is 5 inch by 6 mile and weights 15 ton!!\n" +
                "one of my hobbies is drawing. and when i'm drawing this works great.\n" +
                "I tried to attack it but got meatball all over it.\n" +
                "It only works when I'm Azerbaijan.\n" +
                "I tried to maul it but got onion all over it.\n" +
                "heard about this on compas radio, decided to give it a try.\n" +
                "This Space image works certainly well. It energetically improves my golf by a lot.\n" +
                "The box this comes in is 3 light-year by 5 meter and weights 10 ounce!\n" +
                "It only works when I'm Norway.\n" +
                "This Space image works so well. It hungrily improves my basketball by a lot.\n" +
                "The box this comes in is 5 kilometer by 6 meter and weights 20 ounce!\n" +
                "My neighbor Alida has one of these. She works as a gambler and she says it looks spotless.\n" +
                "this Space image is gracious.\n" +
                "The box this comes in is 5 kilometer by 6 yard and weights 18 gram.\n" +
                "I tried to impale it but got fudge all over it.\n" +
                "one of my hobbies is antique-shopping. and when i'm antique-shopping this works great.\n" +
                "heard about this on bouyon radio, decided to give it a try.\n" +
                "heard about this on compas radio, decided to give it a try.\n" +
                "I tried to vomit it but got bonbon all over it.");
        reviewText.add("SoCal cockroaches are unwelcome, crafty, and tenacious. This Sun pic keeps them away.\n" +
                "My neighbor Eller has one of these. She works as a butler and she says it looks smoky.\n" +
                "The box this comes in is 5 inch by 6 mile and weights 15 ton!!\n" +
                "one of my hobbies is sailing. and when i'm sailing this works great.\n" +
                "My neighbor Krista has one of these. She works as a salesman and she says it looks soapy.\n" +
                "I saw one of these in Spratly Islands and I bought one.\n" +
                "My co-worker Atha has one of these. He says it looks narrow.\n" +
                "talk about optimism!!!\n" +
                "My co-worker Matthew has one of these. He says it looks gigantic.\n" +
                "My co-worker Tyron has one of these. He says it looks stout.\n" +
                "i use it profusely when i'm in my garage.\n" +
                "My co-worker Cato has one of these. He says it looks sopping.\n" +
                "talk about surprise!!!\n" +
                "I tried to hang it but got jelly bean all over it.\n" +
                "This Sun pic works certainly well. It energetically improves my golf by a lot.\n" +
                "This Sun pic works outstandingly well. It beautifully improves my basketball by a lot.\n" +
                "My raven loves to play with it.\n" +
                "this Sun pic is perplexed.\n" +
                "This Sun pic works so well. It hungrily improves my basketball by a lot.\n" +
                "It only works when I'm Bolivia.\n" +
                "one of my hobbies is skateboarding. and when i'm skateboarding this works great.\n" +
                "I tried to shatter it but got potato all over it.\n" +
                "It only works when I'm New Caledonia.\n" +
                "I tried to pinch it but got peanut all over it.\n" +
                "My macaroni penguin loves to play with it.\n" +
                "The box this comes in is 5 kilometer by 6 yard and weights 18 gram.\n" +
                "one of my hobbies is piano. and when i'm playing piano this works great.\n" +
                "talk about irritation.\n" +
                "heard about this on compas radio, decided to give it a try.\n" +
                "My co-worker Tyron has one of these. He says it looks stout.\n" +
                "It only works when I'm Juan de Nova Island.\n" +
                "talk about contempt!\n" +
                "talk about irritation.\n" +
                "The box this comes in is 3 meter by 5 foot and weights 11 kilogram.\n" +
                "I saw one of these in Canada and I bought one.\n" +
                "talk about hatred.\n" +
                "This Sun pic works certainly well. It energetically improves my golf by a lot.\n" +
                "My neighbor Frona has one of these. She works as a gambler and she says it looks bearded.\n" +
                "I tried to nab it but got biscuit all over it.\n" +
                "one of my hobbies is sailing. and when i'm sailing this works great.\n" +
                "It only works when I'm Finland.\n" +
                "My vulture loves to play with it.\n" +
                "My neighbor Isabela has one of these. She works as a taxidermist and she says it looks monochromatic.\n" +
                "My velociraptor loves to play with it.\n" +
                "this Sun pic is mellow.\n" +
                "I saw one of these in The Gambia and I bought one.\n" +
                "My co-worker Knute has one of these. He says it looks smoky.\n" +
                "The box this comes in is 5 kilometer by 6 meter and weights 20 ounce!\n" +
                "My neighbor Karly has one of these. She works as a gambler and she says it looks tall.\n" +
                "i use it never when i'm in my nightclub.");

        reviewRepo.save(new ProductReview(3, "It's an OK picture",users.get(1),products.get(1)));

        // Add Addresses
        addressRepo.save(new Address("1 A St",null,"TX","San Antonio","99999"));
        addressRepo.save(new Address("1 B St",null,"CA","Los Angeles","11111"));
        List<Address> addresses = addressRepo.findAll();

        // Add Orders
        List<Product> cart = new ArrayList<>();
        for (int i=1; i<=10; i++) {
            cart.add(prodRepo.getById(i));
        }
        orderRepo.save(new Order(users.get(1),addresses.get(1),statuses.get(1),cart));


    }

    private static void stars() {
        System.out.println("*****************************************************************************************");
    }
}
