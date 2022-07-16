package com.revature.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.*;
import com.revature.repositories.*;
import com.revature.services.AuthService;
import com.revature.services.jwt.TokenService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    private final Data data = new Data();


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

        // Generate users
        for (int i=0; i< data.getNames().length; i++) {
            String[] name = data.getNames()[i].split(" ");
            String firstName = name[0];
            String lastName = name[1];
            String email = data.getEmails()[i];
            String password = authService.generatePassword("genericPassword"+i);
            userRepo.save(new User(firstName, lastName, email, password, roles.get(1), null, null));
        }
        List<User> users = userRepo.findAll();
//        System.out.println("There are "+users.size()+" users");

        // Add products
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

        // Generate reviews
        for (Product product: products) {
            int reviewsToGenerate = ThreadLocalRandom.current().nextInt(2, 10+1);
            int catId = -1;
            for (int j=0; j<defaultCategories.length; j++) {
                if (defaultCategories[j].equalsIgnoreCase(product.getCategory().getName())) {
                    catId = j;
                }
            }
            int maxLen = data.getReviews()[catId].length;
            for (int i=0; i<reviewsToGenerate; i++) {
                int rating = ThreadLocalRandom.current().nextInt(0, 5+1);
                String desc = data.getReviews()[catId][ThreadLocalRandom.current().nextInt(0, maxLen)];
                int userId = ThreadLocalRandom.current().nextInt(0, userRepo.findAll().size());
                reviewRepo.save(new ProductReview(rating,desc, users.get(userId),product));
            }
        }

        // Add Addresses
        addressRepo.save(new Address("4 Chamois Court","","Pooler","GA","31322"));
        addressRepo.save(new Address("295 Townes Drive","","Nashville","TN","37211"));
        addressRepo.save(new Address("5161 Jefferson Boulevard","#202","Louisville","KY","40219"));
        addressRepo.save(new Address("429 Dennison Ridge Drive","","Manchester","CT","06040"));
        addressRepo.save(new Address("4438 Maine Avenue","","Baldwin Park","CA","91706"));
        addressRepo.save(new Address("510 Leeanne Drive","","Nashville","TN","37211"));
        addressRepo.save(new Address("5461 West Shades Valley Drive","","Montgomery","AL","36108"));
        addressRepo.save(new Address("2032 Gorgas Street","","Montgomery","AL","36106"));
        addressRepo.save(new Address("707 Leaning Oaks Drive","","Savannah","GA","31410"));
        addressRepo.save(new Address("7 Oliver Street","#2","Athol","MA","01331"));
        addressRepo.save(new Address("111 Edgewater Road","#115","Savannah","GA","31406"));
        addressRepo.save(new Address("16 Cabot Street","#2","Everett","MA","02149"));
        addressRepo.save(new Address("904 Walthour Road","","Savannah","GA","31410"));
        addressRepo.save(new Address("2248 South Sutherland Drive","","Montgomery","AL","36116"));
        addressRepo.save(new Address("1002 Cedar Ridge Court","","Annapolis","MD","21403"));
        addressRepo.save(new Address("31130 Meadowbrook Avenue","","Hayward","CA","94544"));
        addressRepo.save(new Address("706 James Road","","Glen Burnie","MD","21061"));
        addressRepo.save(new Address("8125 Glynnwood Drive","","Montgomery","AL","36117"));
        addressRepo.save(new Address("115 Saint John Street","","Manchester","CT","06040"));
        addressRepo.save(new Address("3031 Casa Drive","","Nashville","TN","37214"));
        addressRepo.save(new Address("2504 Longest Avenue","","Louisville","KY","40204"));
        addressRepo.save(new Address("840 Fontaine Road","","Derby","VT","05829"));
        addressRepo.save(new Address("4696 Bull Run Road","","Ashland City","TN","37015"));
        addressRepo.save(new Address("26 Garfield Street","","Bristol","VT","05443"));
        addressRepo.save(new Address("82 Linnmore Drive","","Manchester","CT","06040"));
        addressRepo.save(new Address("7549 Delancey Street","","Youngstown","FL","32466"));
        addressRepo.save(new Address("165 New Hampshire Avenue","","Somerset","MA","02726"));
        addressRepo.save(new Address("7024 Johnny Mercer Boulevard","","Savannah","GA","31410"));
        addressRepo.save(new Address("488 Mill Road","","Hartford","VT","05001"));
        addressRepo.save(new Address("2703 Woolsey Street","#R 1","Berkeley","CA","94705"));
        addressRepo.save(new Address("82 East Foster Street","#2","Melrose","MA","02176"));
        addressRepo.save(new Address("5102 Ander Drive","","Brentwood","TN","37027"));
        addressRepo.save(new Address("131 Kent Drive","","Manchester","CT","06042"));
        addressRepo.save(new Address("3408 Bellisima Place","#204","Louisville","KY","40245"));
        addressRepo.save(new Address("6391 West 60th Avenue","#210","Arvada","CO","80003"));
        addressRepo.save(new Address("29104 Quartz Lane","","Tollhouse","CA","93667"));
        addressRepo.save(new Address("217 West Congress Street","","Savannah","GA","31401"));
        addressRepo.save(new Address("1806 Boscobel Street","","Nashville","TN","37206"));
        addressRepo.save(new Address("2410 Pafford Drive","","Nashville","TN","37206"));
        addressRepo.save(new Address("113 Hammarlee Road","","Glen Burnie","MD","21060"));
        addressRepo.save(new Address("13931 West 87th Drive","","Arvada","CO","80005"));
        addressRepo.save(new Address("10841 Sutter Circle","","Sutter Creek","CA","95685"));
        addressRepo.save(new Address("2433 Southwest 36th Street","","Oklahoma City","OK","73109"));
        addressRepo.save(new Address("3551 North Georgetown Drive","","Montgomery","AL","36109"));
        addressRepo.save(new Address("2619 North Quality Lane","#315","Fayetteville","AR","72703"));
        addressRepo.save(new Address("7404 West Crest Lane","","Glendale","AZ","85310"));
        addressRepo.save(new Address("5921 Ashwood Bluff Drive","","Louisville","KY","40207"));
        addressRepo.save(new Address("324 Martin Luther King Junior Boulevard","","Fayetteville","AR","72701"));
        addressRepo.save(new Address("82 The Commons","","Moretown","VT","05660"));
        addressRepo.save(new Address("18713 Shilstone Way","","Edmond","OK","73012"));
        addressRepo.save(new Address("7952 South Algonquian Way","","Aurora","CO","80016"));
        addressRepo.save(new Address("10301 La Plaza Drive","","Louisville","KY","40272"));
        addressRepo.save(new Address("2560 Michigan Court","","Panama City","FL","32405"));
        addressRepo.save(new Address("201 North Locust Avenue","B","Fayetteville","AR","72701"));
        addressRepo.save(new Address("152 Holly Court","","Mountain View","CA","94043"));
        addressRepo.save(new Address("370 Wallace Road","#D-28","Nashville","TN","37211"));
        addressRepo.save(new Address("408 Pine Street","","Bloomingdale","GA","31302"));
        addressRepo.save(new Address("1995 Nolensville Pike","","Nashville","TN","37211"));
        addressRepo.save(new Address("505 Southeast 32nd Street","","Oklahoma City","OK","73129"));
        addressRepo.save(new Address("265 Airport Road","","Weathersfield","VT","05151"));
        addressRepo.save(new Address("12 Winter Street","","Manchester","CT","06040"));
        addressRepo.save(new Address("6620 North 61st Drive","","Glendale","AZ","85301"));
        addressRepo.save(new Address("1189 Northwest End Avenue","#D3","Fayetteville","AR","72703"));
        addressRepo.save(new Address("704 Crescent Road","","Nashville","TN","37205"));
        addressRepo.save(new Address("3301 Nora Lane","","Louisville","KY","40220"));
        addressRepo.save(new Address("3808 South Smiley Circle","","Montgomery","AL","36108"));
        addressRepo.save(new Address("400 South 28th Street","","Louisville","KY","40212"));
        addressRepo.save(new Address("816 East 69th Street","","Savannah","GA","31405"));
        addressRepo.save(new Address("3420 Horseshoe Circle","","Montgomery","AL","36116"));
        addressRepo.save(new Address("3466 Southview Avenue","","Montgomery","AL","36111"));
        addressRepo.save(new Address("88 Pine Valley Road","","Savannah","GA","31404"));
        addressRepo.save(new Address("36 Grove Street","F","Manchester","CT","06042"));
        addressRepo.save(new Address("327 Idlewylde Drive","#3","Louisville","KY","40206"));
        addressRepo.save(new Address("1751 Shoreham Drive","","Montgomery","AL","36106"));
        addressRepo.save(new Address("8010 Holland Court","D","Arvada","CO","80005"));
        addressRepo.save(new Address("207 Maple Hill Road","","Shaftsbury","VT","05262"));
        addressRepo.save(new Address("12 Fletcher Lane","","Shelburne","VT","05482"));
        addressRepo.save(new Address("2220 Kirk Avenue","","Nashville","TN","37218"));
        addressRepo.save(new Address("2708 Mabel Street","M","Berkeley","CA","94702"));
        addressRepo.save(new Address("12013 Blue Moon Avenue","","Oklahoma City","OK","73162"));
        addressRepo.save(new Address("7758 Betty Louise Drive","","Panama City","FL","32404"));
        addressRepo.save(new Address("9222 Sandra Grace Road","","Southport","FL","32409"));
        addressRepo.save(new Address("2572 Drake Street","","Montgomery","AL","36108"));
        addressRepo.save(new Address("104 Bennington Street","","Lawrence","MA","01841"));
        addressRepo.save(new Address("5950 West Missouri Avenue","#148","Glendale","AZ","85301"));
        addressRepo.save(new Address("129 Lark Lane","","Farmington","AR","72730"));
        addressRepo.save(new Address("190 High Street","#213C","Medford","MA","02155"));
        addressRepo.save(new Address("4738 Mallard Common","","Fremont","CA","94555"));
        addressRepo.save(new Address("2021 West Burnett Avenue","","Louisville","KY","40210"));
        addressRepo.save(new Address("102 Derondo Street","","Panama City Beach","FL","32413"));
        addressRepo.save(new Address("5606 Olde Wadsworth Boulevard","#20","Arvada","CO","80002"));
        addressRepo.save(new Address("5747 West Missouri Avenue","#122","Glendale","AZ","85301"));
        addressRepo.save(new Address("168 Partridge Hill Road","","Charlton","MA","01507"));
        addressRepo.save(new Address("243 Kentucky Avenue","","Pasadena","MD","21122"));
        addressRepo.save(new Address("13066 North 56th Avenue","","Glendale","AZ","85304"));
        addressRepo.save(new Address("3110 East Victory Drive","","Savannah","GA","31404"));
        addressRepo.save(new Address("1842 West Park Place","","Oklahoma City","OK","73106"));
        addressRepo.save(new Address("64 Roseberry Circle","","Port Wentworth","GA","31407"));
        addressRepo.save(new Address("11113 North Miller Avenue","","Oklahoma City","OK","73120"));
        addressRepo.save(new Address("143 Canton Court","","Goodlettsville","TN","37072"));
        addressRepo.save(new Address("3528 Seasons Drive","","Nashville","TN","37013"));
        addressRepo.save(new Address("2728 Hale Avenue","","Louisville","KY","40211"));
        addressRepo.save(new Address("204 Blue Hills Drive","","Nashville","TN","37214"));
        addressRepo.save(new Address("10301 La Plaza Drive","","Louisville","KY","40272"));
        addressRepo.save(new Address("7419 West Hill Lane","","Glendale","AZ","85310"));
        addressRepo.save(new Address("9545 West 74th Avenue","","Arvada","CO","80005"));
        addressRepo.save(new Address("416 South University Boulevard","","Norman","OK","73069"));
        addressRepo.save(new Address("2200 East Victory Drive","#36","Savannah","GA","31404"));
        addressRepo.save(new Address("16502 South Main Street","#5","Gardena","CA","90248"));
        addressRepo.save(new Address("116 Frost Park","","Hartford","VT","05001"));
        addressRepo.save(new Address("2504 Longest Avenue","","Louisville","KY","40204"));
        addressRepo.save(new Address("118 Pearl Street","","Manchester","CT","06040"));
        addressRepo.save(new Address("1383 Purdue Street","","San Leandro","CA","94579"));
        addressRepo.save(new Address("44 Downey Drive","","Manchester","CT","06040"));
        addressRepo.save(new Address("5938 Laguna Honda Street","","Redding","CA","96001"));
        addressRepo.save(new Address("1528 Stafford Avenue","","Hayward","CA","94541"));
        addressRepo.save(new Address("1215 Joseph Avenue","","Nashville","TN","37207"));
        addressRepo.save(new Address("634 Chautauqua Avenue","","Norman","OK","73069"));
        addressRepo.save(new Address("200 Hialeah Drive","","Glen Burnie","MD","21060"));
        addressRepo.save(new Address("11 Proctor Circle","","Peabody","MA","01960"));
        addressRepo.save(new Address("141 West Leslie Lane","","Panama City Beach","FL","32407"));
        addressRepo.save(new Address("31 Ashworth Street","","Manchester","CT","06040"));
        addressRepo.save(new Address("37 Kensington Street","","Manchester","CT","06040"));
        addressRepo.save(new Address("7326 North 62nd Avenue","#2","Glendale","AZ","85301"));
        addressRepo.save(new Address("200 Redwood Road","","Manchester","CT","06040"));
        addressRepo.save(new Address("6108 Iris Way","","Arvada","CO","80004"));
        addressRepo.save(new Address("2100 Sandy Creek Trail","","Edmond","OK","73013"));
        addressRepo.save(new Address("325 Baxter Lane","","Fayetteville","AR","72701"));
        addressRepo.save(new Address("2375 Main Street","B","Palmer","MA","01069"));
        addressRepo.save(new Address("7650 West 68th Avenue","#315","Arvada","CO","80004"));
        addressRepo.save(new Address("115 Falkirk Street","","Savannah","GA","31407"));
        addressRepo.save(new Address("597 East Miracle Drive","#4","Fayetteville","AR","72701"));
        addressRepo.save(new Address("68 Princeton Street","","Manchester","CT","06042"));
        addressRepo.save(new Address("1056 Cayer Drive","","Glen Burnie","MD","21061"));
        addressRepo.save(new Address("4455 West 61st Place","","Arvada","CO","80003"));
        addressRepo.save(new Address("172 Homestead Street","","Manchester","CT","06042"));
        addressRepo.save(new Address("6716 South Mariposa Lane","","Dublin","CA","94568"));
        addressRepo.save(new Address("2237 Northwest 18th Street","","Oklahoma City","OK","73107"));
        addressRepo.save(new Address("2504 Longest Avenue","","Louisville","KY","40204"));
        addressRepo.save(new Address("415 West 42nd Street","","Savannah","GA","31401"));
        addressRepo.save(new Address("309 Water Street","","North Little Rock","AR","72117"));
        addressRepo.save(new Address("7000 Hugh Drive","","Panama City","FL","32404"));
        addressRepo.save(new Address("2641 Heather Lane","","Redding","CA","96002"));
        addressRepo.save(new Address("2765 North Barcelona Avenue","","Fayetteville","AR","72703"));
        addressRepo.save(new Address("4408 Bonaparte Boulevard","","Midwest City","OK","73110"));
        addressRepo.save(new Address("5420 Allison Street","#202","Arvada","CO","80002"));
        addressRepo.save(new Address("1056 Cayer Drive","","Glen Burnie","MD","21061"));
        addressRepo.save(new Address("119 Redwood Road","","Manchester","CT","06040"));
        addressRepo.save(new Address("9399 Yucca Way","","Arvada","CO","80007"));
        addressRepo.save(new Address("4714 Narrow Lane Road","","Montgomery","AL","36116"));
        addressRepo.save(new Address("82 East Foster Street","#2","Melrose","MA","02176"));
        addressRepo.save(new Address("1243 Doris Avenue","","Pasadena","MD","21122"));
        addressRepo.save(new Address("7785 Montgomery Mews Court","","Severn","MD","21144"));
        addressRepo.save(new Address("21402 Caribbean Lane","","Panama City Beach","FL","32413"));
        addressRepo.save(new Address("1250 Pram Place","","Annapolis","MD","21403"));
        addressRepo.save(new Address("1713 Arrow Cove Lane","","Annapolis","MD","21401"));
        addressRepo.save(new Address("5108 Franklin Street","","Savannah","GA","31405"));
        addressRepo.save(new Address("3114 US Highway 98","","Mexico Beach","FL","32456"));
        addressRepo.save(new Address("2212 Daffin Drive","","Savannah","GA","31404"));
        addressRepo.save(new Address("3377 Sandstone Court","","Pleasanton","CA","94588"));
        addressRepo.save(new Address("320 Union Station Way","","Calera","AL","35040"));
        addressRepo.save(new Address("3538 Mendenhall Court","","Pleasanton","CA","94588"));
        addressRepo.save(new Address("649 Praderia Circle","","Fremont","CA","94539"));
        addressRepo.save(new Address("3300 Crystal Road","","Montgomery","AL","36110"));
        addressRepo.save(new Address("6754 Field Street","","Arvada","CO","80004"));
        addressRepo.save(new Address("43 Westminster Street","","Pittsfield","MA","01201"));
        addressRepo.save(new Address("7 Rose Dhu Lane","","Savannah","GA","31419"));
        addressRepo.save(new Address("8526 Preston Highway","","Louisville","KY","40219"));
        addressRepo.save(new Address("2654 Quiet Water Cove","","Annapolis","MD","21401"));
        addressRepo.save(new Address("95 Westbourne Way","","Pooler","GA","31322"));
        addressRepo.save(new Address("16205 Wind Crest Way","","Edmond","OK","73013"));
        addressRepo.save(new Address("4331 Foeburn Lane","","Louisville","KY","40207"));
        addressRepo.save(new Address("30 Tanner Street","","Manchester","CT","06042"));
        addressRepo.save(new Address("21 Glenoak Lane Northwest","B","Glen Burnie","MD","21061"));
        addressRepo.save(new Address("5300 Robinwood Road","","Louisville","KY","40218"));
        addressRepo.save(new Address("5209 North Dewey Avenue","","Oklahoma City","OK","73118"));
        addressRepo.save(new Address("514 East 38th Street","","Savannah","GA","31401"));
        addressRepo.save(new Address("2317 Fernwood Drive","","Nashville","TN","37216"));
        addressRepo.save(new Address("464 Springfield Street","","Wilbraham","MA","01095"));
        addressRepo.save(new Address("5 Meadow Lane","","Rutland","VT","05701"));
        addressRepo.save(new Address("34 Belair Drive","","Holbrook","MA","02343"));
        addressRepo.save(new Address("107 Laurel Green Court","","Savannah","GA","31419"));
        addressRepo.save(new Address("5 Centercrest Drive","","Tyngsborough","MA","01879"));
        addressRepo.save(new Address("2100 North Leverett Avenue","#112J","Fayetteville","AR","72703"));
        addressRepo.save(new Address("37122 Contra Costa Avenue","","Fremont","CA","94536"));
        addressRepo.save(new Address("22538 6th Street","","Hayward","CA","94541"));
        addressRepo.save(new Address("910 Arlington Terrace","","Fayetteville","AR","72701"));
        addressRepo.save(new Address("10312 Parlett Place","","Cupertino","CA","95014"));
        addressRepo.save(new Address("6605 North 93RD Avenue","#1016","Glendale","AZ","85305"));
        addressRepo.save(new Address("8 Kozera Avenue","","Hadley","MA","01035"));
        addressRepo.save(new Address("3537 West Chevaux Drive","","Fayetteville","AR","72704"));
        addressRepo.save(new Address("5110 East Shoshone Avenue","","Orange","CA","92867"));
        addressRepo.save(new Address("1583 Elberta Court","","Severn","MD","21144"));
        addressRepo.save(new Address("17 Gregory Street","","Middleton","MA","01949"));
        addressRepo.save(new Address("179 Blue Ridge Drive","","Manchester","CT","06040"));
        addressRepo.save(new Address("607 North 46th Avenue","","Fayetteville","AR","72704"));
        addressRepo.save(new Address("150 Meadowview Street","","Marshfield","MA","02050"));
        addressRepo.save(new Address("17722 North 79th Avenue","#1135","Glendale","AZ","85308"));
        addressRepo.save(new Address("200 Hialeah Drive","","Glen Burnie","MD","21060"));
        addressRepo.save(new Address("301 Willow Way","","Lynn Haven","FL","32444"));
        List<Address> addresses = addressRepo.findAll();

        // Generate Orders
        for (int i=0; i<50; i++) {
            int userId = ThreadLocalRandom.current().nextInt(0, users.size());
            User user = users.get(userId);
            int addressId = ThreadLocalRandom.current().nextInt(0, addresses.size());
            Address address = addresses.get(addressId);
            OrderStatus status;
            switch(ThreadLocalRandom.current().nextInt(0, 6)) {
                case 1:
                    status = statuses.get(2);
                    break;
                case 2:
                    status = statuses.get(3);
                    break;
                default:
                    status = statuses.get(1);
            }
            int amountOfItems = ThreadLocalRandom.current().nextInt(1, 15+1);
            List<Product> cart = new ArrayList<>();
            List<Integer> prodIds = new ArrayList<>();
            for (int j = 0; j<amountOfItems; j++) {
                int prodId = ThreadLocalRandom.current().nextInt(0, products.size());
                if(!prodIds.contains(prodId)) {
                    prodIds.add(prodId);
                    cart.add(products.get(prodId));
                }
            }
            orderRepo.save(new Order(user,address,status,cart));
        }
    }

    private static void stars() {
        for (int i=0; i<10; i++) {
            System.out.print("**********");
        }
        System.out.println();
    }
}
