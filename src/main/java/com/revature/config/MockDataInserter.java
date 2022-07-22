package com.revature.config;

import com.revature.dtos.Principal;
import com.revature.models.*;
import com.revature.repositories.*;
import com.revature.services.AuthService;
import com.revature.services.jwt.TokenService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class populates all tables with data
 * It normally runs in 3-4 seconds
 */

@Component
@Profile("default || local || test")
public class MockDataInserter implements CommandLineRunner {

    private final AddressRepository addressRepo;
    private final CategoryRepository catRepo;
    private final OrderRepository orderRepo;
    private final OrderStatusRepository statusRepo;
    private final ProductRepository prodRepo;
    private final ProductReviewRepository reviewRepo;
    private final UserRepository userRepo;
    private final UserRoleRepository roleRepo;
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
        long startTime = System.nanoTime();

        // Populate table "category"
        List<Category> cats = new ArrayList<>();
        String[] defaultCategories = {"Cloud","Dawn","Day","Dusk","Moon","Night","Space","Sun"};
        // Alphabetical order to sync with external Python generation script
        for (String cat: defaultCategories) {
            cats.add(new Category(cat)); // persist each category in order
        }
        catRepo.saveAll(cats);

        // Populate table "user_role"
        List<UserRole> roles = new ArrayList<>();
        String[] defaultRoles = {"Admin","Basic"};
        // Ordered by decreasing permission
        for (String role: defaultRoles) {
            roles.add(new UserRole(role.toUpperCase())); // persist roles in UPPERCASE
        }
        roleRepo.saveAll(roles);

        // Populate table "order_status
        List<OrderStatus> statuses = new ArrayList<>();
        String[] defaultStatuses = {"Cart","Pending","Shipped","Delivered","Canceled"};
        // Chronological order
        for (String status: defaultStatuses) {
            statuses.add(new OrderStatus(status)); // persist in order
        }
        statusRepo.saveAll(statuses);

        Boolean testRSA = false;
        if (testRSA) { // tests maximum email size RSA key-size permits
            stars();
            int n = 260; // starting email length
            StringBuilder email50Length = new StringBuilder(n);
            for (int i = 0; i < n; i++) {
                email50Length.append("@");
            }
            String currentEmail = email50Length.toString();
            Principal prin;
            for (int i=0; i<500; i++) { // add up to 500 to starting email length
                currentEmail += "@";
                System.out.println("Testing length " + currentEmail.length());
                prin = new Principal(Integer.MAX_VALUE, currentEmail);
                String token = service.generateToken(prin);
                prin = service.extractTokenDetails(token);
                System.out.println(prin);
            }
            stars();
        }



        // Populate table "user"
        List<User> users = new ArrayList<>();
        users.add(new User("Admin","Admin","Admin@SkyView.com",
                authService.generatePassword("Admin12@"),
                roles.get(0), // ADMIN user
                null,null)); // inserted with no reviews nor orders

        users.add(new User("Tester","McTesterson","Tester1@revature.net",
                authService.generatePassword("Tester12@"),
                roles.get(1), // BASIC user
                null,null)); // inserted with no reviews nor orders

        // Generate users
        for (int i=0; i< data.getNames().length; i++) { // generate as many users as data.class has names for them
            String[] name = data.getNames()[i].split(" "); // split into first, last
            String firstName = name[0];
            String lastName = name[1];
            String email = data.getEmails()[i]; // get an email for the user
            String password = UUID.randomUUID().toString();; // UUID for fast startup
            users.add(new User(firstName, lastName, email, password, roles.get(1), null, null));
        }
        userRepo.saveAll(users); // handy list for use here

        // Populate table "product"; this code was generated in Python from image metadata
        // TODO : Move the data to data.class and make this a loop
        String base_url = "https://raw.githubusercontent.com/jsparks9/pics/main/images-by-category/";
        List<Product> products = new ArrayList<>(); // products.add()
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",2.95,base_url+"cloud/small/1.jpg",base_url+"cloud/medium/1.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",1.45,base_url+"cloud/small/10.jpg",base_url+"cloud/medium/10.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",0.95,base_url+"cloud/small/11.jpg",base_url+"cloud/medium/11.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",1.95,base_url+"cloud/small/12.jpg",base_url+"cloud/medium/12.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",0.95,base_url+"cloud/small/13.jpg",base_url+"cloud/medium/13.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",2.45,base_url+"cloud/small/14.jpg",base_url+"cloud/medium/14.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",2.95,base_url+"cloud/small/15.jpg",base_url+"cloud/medium/15.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",1.95,base_url+"cloud/small/16.jpg",base_url+"cloud/medium/16.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",0.45,base_url+"cloud/small/17.jpg",base_url+"cloud/medium/17.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",0.95,base_url+"cloud/small/18.jpg",base_url+"cloud/medium/18.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",1.95,base_url+"cloud/small/19.jpg",base_url+"cloud/medium/19.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",0.45,base_url+"cloud/small/2.jpg",base_url+"cloud/medium/2.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",0.45,base_url+"cloud/small/20.jpg",base_url+"cloud/medium/20.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",2.45,base_url+"cloud/small/3.jpg",base_url+"cloud/medium/3.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",0.45,base_url+"cloud/small/4.jpg",base_url+"cloud/medium/4.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",2.45,base_url+"cloud/small/5.jpg",base_url+"cloud/medium/5.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",2.95,base_url+"cloud/small/6.jpg",base_url+"cloud/medium/6.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",0.95,base_url+"cloud/small/7.jpg",base_url+"cloud/medium/7.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",1.45,base_url+"cloud/small/8.jpg",base_url+"cloud/medium/8.jpg"));
        products.add(new Product(cats.get(0),"cloud picture","cloud picture",2.95,base_url+"cloud/small/9.jpg",base_url+"cloud/medium/9.jpg"));
        products.add(new Product(cats.get(1),"dawn picture","dawn picture",1.45,base_url+"dawn/small/1.jpg",base_url+"dawn/medium/1.jpg"));
        products.add(new Product(cats.get(1),"dawn picture","dawn picture",0.95,base_url+"dawn/small/2.jpg",base_url+"dawn/medium/2.jpg"));
        products.add(new Product(cats.get(1),"dawn picture","dawn picture",0.45,base_url+"dawn/small/3.jpg",base_url+"dawn/medium/3.jpg"));
        products.add(new Product(cats.get(1),"dawn picture","dawn picture",1.95,base_url+"dawn/small/4.jpg",base_url+"dawn/medium/4.jpg"));
        products.add(new Product(cats.get(1),"dawn picture","dawn picture",0.95,base_url+"dawn/small/5.jpg",base_url+"dawn/medium/5.jpg"));
        products.add(new Product(cats.get(1),"dawn picture","dawn picture",0.45,base_url+"dawn/small/6.jpg",base_url+"dawn/medium/6.jpg"));
        products.add(new Product(cats.get(1),"dawn picture","dawn picture",1.45,base_url+"dawn/small/7.jpg",base_url+"dawn/medium/7.jpg"));
        products.add(new Product(cats.get(1),"dawn picture","dawn picture",0.45,base_url+"dawn/small/8.jpg",base_url+"dawn/medium/8.jpg"));
        products.add(new Product(cats.get(1),"Amazon Rain Forest","Amazon Rain Forest",0.95,base_url+"dawn/small/Amazon%20Rain%20Forest.jpg",base_url+"dawn/medium/Amazon%20Rain%20Forest.jpg"));
        products.add(new Product(cats.get(1),"australia-shoalhaven-heads","australia-shoalhaven-heads",0.95,base_url+"dawn/small/australia-shoalhaven-heads.jpg",base_url+"dawn/medium/australia-shoalhaven-heads.jpg"));
        products.add(new Product(cats.get(1),"Cocoa Beach, FL, USA","Cocoa Beach, FL, USA",0.95,base_url+"dawn/small/Cocoa%20Beach%2C%20FL%2C%20USA.jpg",base_url+"dawn/medium/Cocoa%20Beach%2C%20FL%2C%20USA.jpg"));
        products.add(new Product(cats.get(1),"Emerald Bay State Park, South Lake Tahoe, United States","Emerald Bay State Park, South Lake Tahoe, United States",0.45,base_url+"dawn/small/Emerald%20Bay%20State%20Park%2C%20South%20Lake%20Tahoe%2C%20United%20States.jpg",base_url+"dawn/medium/Emerald%20Bay%20State%20Park%2C%20South%20Lake%20Tahoe%2C%20United%20States.jpg"));
        products.add(new Product(cats.get(1),"Luttach, Italy from balcony","Luttach, Italy from balcony",2.45,base_url+"dawn/small/Luttach%2C%20Italy%20from%20balcony.jpg",base_url+"dawn/medium/Luttach%2C%20Italy%20from%20balcony.jpg"));
        products.add(new Product(cats.get(1),"morning Nepal","morning Nepal",1.45,base_url+"dawn/small/morning%20Nepal.jpg",base_url+"dawn/medium/morning%20Nepal.jpg"));
        products.add(new Product(cats.get(1),"Mount Tamalpais, United States","Mount Tamalpais, United States",2.95,base_url+"dawn/small/Mount%20Tamalpais%2C%20United%20States.jpg",base_url+"dawn/medium/Mount%20Tamalpais%2C%20United%20States.jpg"));
        products.add(new Product(cats.get(1),"Mt Mitchell State Park, Burnsville, United States","Mt Mitchell State Park, Burnsville, United States",0.45,base_url+"dawn/small/Mt%20Mitchell%20State%20Park%2C%20Burnsville%2C%20United%20States.jpg",base_url+"dawn/medium/Mt%20Mitchell%20State%20Park%2C%20Burnsville%2C%20United%20States.jpg"));
        products.add(new Product(cats.get(1),"Pacific Northwest","Pacific Northwest",1.45,base_url+"dawn/small/Pacific%20Northwest.jpg",base_url+"dawn/medium/Pacific%20Northwest.jpg"));
        products.add(new Product(cats.get(1),"San Diego, United States","San Diego, United States",0.95,base_url+"dawn/small/San%20Diego%2C%20United%20States.jpg",base_url+"dawn/medium/San%20Diego%2C%20United%20States.jpg"));
        products.add(new Product(cats.get(1),"Summer time Adrasan, Turkey","Summer time Adrasan, Turkey",2.95,base_url+"dawn/small/Summer%20time%20Adrasan%2C%20Turkey.jpg",base_url+"dawn/medium/Summer%20time%20Adrasan%2C%20Turkey.jpg"));
        products.add(new Product(cats.get(1),"Toluca, Mexico","Toluca, Mexico",2.45,base_url+"dawn/small/Toluca%2C%20Mexico.jpg",base_url+"dawn/medium/Toluca%2C%20Mexico.jpg"));
        products.add(new Product(cats.get(2),"day picture","day picture",1.95,base_url+"day/small/1.jpg",base_url+"day/medium/1.jpg"));
        products.add(new Product(cats.get(2),"day picture","day picture",1.95,base_url+"day/small/10.jpg",base_url+"day/medium/10.jpg"));
        products.add(new Product(cats.get(2),"day picture","day picture",2.45,base_url+"day/small/11.jpg",base_url+"day/medium/11.jpg"));
        products.add(new Product(cats.get(2),"day picture","day picture",1.95,base_url+"day/small/12.jpg",base_url+"day/medium/12.jpg"));
        products.add(new Product(cats.get(2),"day picture","day picture",2.45,base_url+"day/small/2.jpg",base_url+"day/medium/2.jpg"));
        products.add(new Product(cats.get(2),"day picture","day picture",2.95,base_url+"day/small/3.jpg",base_url+"day/medium/3.jpg"));
        products.add(new Product(cats.get(2),"day picture","day picture",1.45,base_url+"day/small/4.jpg",base_url+"day/medium/4.jpg"));
        products.add(new Product(cats.get(2),"day picture","day picture",0.45,base_url+"day/small/5.jpg",base_url+"day/medium/5.jpg"));
        products.add(new Product(cats.get(2),"day picture","day picture",1.95,base_url+"day/small/6.jpg",base_url+"day/medium/6.jpg"));
        products.add(new Product(cats.get(2),"day picture","day picture",0.45,base_url+"day/small/7.jpg",base_url+"day/medium/7.jpg"));
        products.add(new Product(cats.get(2),"day picture","day picture",0.45,base_url+"day/small/8.jpg",base_url+"day/medium/8.jpg"));
        products.add(new Product(cats.get(2),"day picture","day picture",2.95,base_url+"day/small/9.jpg",base_url+"day/medium/9.jpg"));
        products.add(new Product(cats.get(2),"Almaty Region, Kazakhstan","Almaty Region, Kazakhstan",0.45,base_url+"day/small/Almaty%20Region%2C%20Kazakhstan.jpg",base_url+"day/medium/Almaty%20Region%2C%20Kazakhstan.jpg"));
        products.add(new Product(cats.get(2),"Baloons","Baloons",2.45,base_url+"day/small/Baloons.jpg",base_url+"day/medium/Baloons.jpg"));
        products.add(new Product(cats.get(2),"Castaic Lake, California, USA","Castaic Lake, California, USA",2.45,base_url+"day/small/Castaic%20Lake%2C%20California%2C%20USA.jpg",base_url+"day/medium/Castaic%20Lake%2C%20California%2C%20USA.jpg"));
        products.add(new Product(cats.get(2),"Crissy Field, San Francisco, United States","Crissy Field, San Francisco, United States",1.45,base_url+"day/small/Crissy%20Field%2C%20San%20Francisco%2C%20United%20States.jpg",base_url+"day/medium/Crissy%20Field%2C%20San%20Francisco%2C%20United%20States.jpg"));
        products.add(new Product(cats.get(2),"Gava, Spain","Gava, Spain",1.45,base_url+"day/small/Gava%2C%20Spain.jpg",base_url+"day/medium/Gava%2C%20Spain.jpg"));
        products.add(new Product(cats.get(2),"Giraffe","Giraffe",2.95,base_url+"day/small/Giraffe.jpg",base_url+"day/medium/Giraffe.jpg"));
        products.add(new Product(cats.get(2),"Lighthouse","Lighthouse",0.45,base_url+"day/small/Lighthouse.jpg",base_url+"day/medium/Lighthouse.jpg"));
        products.add(new Product(cats.get(2),"Seaford, UK","Seaford, UK",2.95,base_url+"day/small/Seaford%2C%20UK.jpg",base_url+"day/medium/Seaford%2C%20UK.jpg"));
        products.add(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/1.jpg",base_url+"dusk/medium/1.jpg"));
        products.add(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/10.jpg",base_url+"dusk/medium/10.jpg"));
        products.add(new Product(cats.get(3),"dusk picture","dusk picture",2.45,base_url+"dusk/small/11.jpg",base_url+"dusk/medium/11.jpg"));
        products.add(new Product(cats.get(3),"dusk picture","dusk picture",0.45,base_url+"dusk/small/2.jpg",base_url+"dusk/medium/2.jpg"));
        products.add(new Product(cats.get(3),"dusk picture","dusk picture",0.95,base_url+"dusk/small/3.jpg",base_url+"dusk/medium/3.jpg"));
        products.add(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/4.jpg",base_url+"dusk/medium/4.jpg"));
        products.add(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/5.jpg",base_url+"dusk/medium/5.jpg"));
        products.add(new Product(cats.get(3),"dusk picture","dusk picture",2.45,base_url+"dusk/small/6.jpg",base_url+"dusk/medium/6.jpg"));
        products.add(new Product(cats.get(3),"dusk picture","dusk picture",2.95,base_url+"dusk/small/7.jpg",base_url+"dusk/medium/7.jpg"));
        products.add(new Product(cats.get(3),"dusk picture","dusk picture",0.45,base_url+"dusk/small/8.jpg",base_url+"dusk/medium/8.jpg"));
        products.add(new Product(cats.get(3),"dusk picture","dusk picture",0.45,base_url+"dusk/small/9.jpg",base_url+"dusk/medium/9.jpg"));
        products.add(new Product(cats.get(3),"Ankara, Türkiye","Ankara, Türkiye",1.45,base_url+"dusk/small/Ankara%2C%20Türkiye.jpg",base_url+"dusk/medium/Ankara%2C%20Türkiye.jpg"));
        products.add(new Product(cats.get(3),"Countryside summer","Countryside summer",0.95,base_url+"dusk/small/Countryside%20summer.jpg",base_url+"dusk/medium/Countryside%20summer.jpg"));
        products.add(new Product(cats.get(3),"Dawn dusk sunset view from hilly coastline","Dawn dusk sunset view from hilly coastline",0.45,base_url+"dusk/small/Dawn%20dusk%20sunset%20view%20from%20hilly%20coastline.jpg",base_url+"dusk/medium/Dawn%20dusk%20sunset%20view%20from%20hilly%20coastline.jpg"));
        products.add(new Product(cats.get(3),"Dique de Ullum, San Juan, Argentina","Dique de Ullum, San Juan, Argentina",1.95,base_url+"dusk/small/Dique%20de%20Ullum%2C%20San%20Juan%2C%20Argentina.jpg",base_url+"dusk/medium/Dique%20de%20Ullum%2C%20San%20Juan%2C%20Argentina.jpg"));
        products.add(new Product(cats.get(3),"Haaksbergen, Haaksbergen, Nederland","Haaksbergen, Haaksbergen, Nederland",1.95,base_url+"dusk/small/Haaksbergen%2C%20Haaksbergen%2C%20Nederland.jpg",base_url+"dusk/medium/Haaksbergen%2C%20Haaksbergen%2C%20Nederland.jpg"));
        products.add(new Product(cats.get(3),"La Jolla, California","La Jolla, California",0.45,base_url+"dusk/small/La%20Jolla%2C%20California.jpg",base_url+"dusk/medium/La%20Jolla%2C%20California.jpg"));
        products.add(new Product(cats.get(3),"Melrose, US orange sunset","Melrose, US orange sunset",2.95,base_url+"dusk/small/Melrose%2C%20US%20orange%20sunset.jpg",base_url+"dusk/medium/Melrose%2C%20US%20orange%20sunset.jpg"));
        products.add(new Product(cats.get(3),"Melrose, USA","Melrose, USA",1.45,base_url+"dusk/small/Melrose%2C%20USA.jpg",base_url+"dusk/medium/Melrose%2C%20USA.jpg"));
        products.add(new Product(cats.get(3),"Sestroretsk, Russia","Sestroretsk, Russia",1.95,base_url+"dusk/small/Sestroretsk%2C%20Russia.jpg",base_url+"dusk/medium/Sestroretsk%2C%20Russia.jpg"));
        products.add(new Product(cats.get(3),"Shelly Beach, Coromandel, New Zealand","Shelly Beach, Coromandel, New Zealand",0.95,base_url+"dusk/small/Shelly%20Beach%2C%20Coromandel%2C%20New%20Zealand.jpg",base_url+"dusk/medium/Shelly%20Beach%2C%20Coromandel%2C%20New%20Zealand.jpg"));
        products.add(new Product(cats.get(3),"Sundbyberg, Sweden, Duvbo","Sundbyberg, Sweden, Duvbo",1.45,base_url+"dusk/small/Sundbyberg%2C%20Sweden%2C%20Duvbo.jpg",base_url+"dusk/medium/Sundbyberg%2C%20Sweden%2C%20Duvbo.jpg"));
        products.add(new Product(cats.get(3),"Sunset at Champion park, Austin, Texas","Sunset at Champion park, Austin, Texas",0.95,base_url+"dusk/small/Sunset%20at%20Champion%20park%2C%20Austin%2C%20Texas.jpg",base_url+"dusk/medium/Sunset%20at%20Champion%20park%2C%20Austin%2C%20Texas.jpg"));
        products.add(new Product(cats.get(3),"Uluru, Petermann Northern Territory, Australia","Uluru, Petermann Northern Territory, Australia",1.45,base_url+"dusk/small/Uluru%2C%20Petermann%20Northern%20Territory%2C%20Australia.jpg",base_url+"dusk/medium/Uluru%2C%20Petermann%20Northern%20Territory%2C%20Australia.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",0.95,base_url+"moon/small/1.jpg",base_url+"moon/medium/1.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",1.95,base_url+"moon/small/10.jpg",base_url+"moon/medium/10.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",1.95,base_url+"moon/small/11.jpg",base_url+"moon/medium/11.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",2.95,base_url+"moon/small/12.jpg",base_url+"moon/medium/12.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/13.jpg",base_url+"moon/medium/13.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",1.45,base_url+"moon/small/14.jpg",base_url+"moon/medium/14.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/15.jpg",base_url+"moon/medium/15.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/16.jpg",base_url+"moon/medium/16.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",0.95,base_url+"moon/small/17.jpg",base_url+"moon/medium/17.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/18.jpg",base_url+"moon/medium/18.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",0.95,base_url+"moon/small/19.jpg",base_url+"moon/medium/19.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/2.jpg",base_url+"moon/medium/2.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",0.95,base_url+"moon/small/20.jpg",base_url+"moon/medium/20.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/3.jpg",base_url+"moon/medium/3.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",2.95,base_url+"moon/small/4.jpg",base_url+"moon/medium/4.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/5.jpg",base_url+"moon/medium/5.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/6.jpg",base_url+"moon/medium/6.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",0.45,base_url+"moon/small/7.jpg",base_url+"moon/medium/7.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/8.jpg",base_url+"moon/medium/8.jpg"));
        products.add(new Product(cats.get(4),"moon picture","moon picture",2.45,base_url+"moon/small/9.jpg",base_url+"moon/medium/9.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",1.95,base_url+"night/small/1.jpg",base_url+"night/medium/1.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/10.jpg",base_url+"night/medium/10.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/11.jpg",base_url+"night/medium/11.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/12.jpg",base_url+"night/medium/12.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",0.95,base_url+"night/small/13.jpg",base_url+"night/medium/13.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/14.jpg",base_url+"night/medium/14.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/15.jpg",base_url+"night/medium/15.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",0.45,base_url+"night/small/16.jpg",base_url+"night/medium/16.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/17.jpg",base_url+"night/medium/17.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",1.45,base_url+"night/small/18.jpg",base_url+"night/medium/18.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/19.jpg",base_url+"night/medium/19.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",0.95,base_url+"night/small/2.jpg",base_url+"night/medium/2.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/20.jpg",base_url+"night/medium/20.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",1.95,base_url+"night/small/3.jpg",base_url+"night/medium/3.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",1.45,base_url+"night/small/4.jpg",base_url+"night/medium/4.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",2.45,base_url+"night/small/5.jpg",base_url+"night/medium/5.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",1.45,base_url+"night/small/6.jpg",base_url+"night/medium/6.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",1.95,base_url+"night/small/7.jpg",base_url+"night/medium/7.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",2.95,base_url+"night/small/8.jpg",base_url+"night/medium/8.jpg"));
        products.add(new Product(cats.get(5),"night picture","night picture",0.45,base_url+"night/small/9.jpg",base_url+"night/medium/9.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/1.jpg",base_url+"space/medium/1.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",0.95,base_url+"space/small/10.jpg",base_url+"space/medium/10.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",2.45,base_url+"space/small/11.jpg",base_url+"space/medium/11.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",1.95,base_url+"space/small/12.jpg",base_url+"space/medium/12.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/13.jpg",base_url+"space/medium/13.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/14.jpg",base_url+"space/medium/14.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",2.45,base_url+"space/small/15.jpg",base_url+"space/medium/15.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",0.95,base_url+"space/small/2.jpg",base_url+"space/medium/2.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",1.95,base_url+"space/small/3.jpg",base_url+"space/medium/3.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/4.jpg",base_url+"space/medium/4.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",0.45,base_url+"space/small/5.jpg",base_url+"space/medium/5.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",2.45,base_url+"space/small/6.jpg",base_url+"space/medium/6.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",2.95,base_url+"space/small/7.jpg",base_url+"space/medium/7.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",1.45,base_url+"space/small/8.jpg",base_url+"space/medium/8.jpg"));
        products.add(new Product(cats.get(6),"space picture","space picture",2.95,base_url+"space/small/9.jpg",base_url+"space/medium/9.jpg"));
        products.add(new Product(cats.get(6),"A simulated view of Mars as it would be seen from the Mars Global Surveyor spacecraft","A simulated view of Mars as it would be seen from the Mars Global Surveyor spacecraft",0.45,base_url+"space/small/A%20simulated%20view%20of%20Mars%20as%20it%20would%20be%20seen%20from%20the%20Mars%20Global%20Surveyor%20spacecraft.jpg",base_url+"space/medium/A%20simulated%20view%20of%20Mars%20as%20it%20would%20be%20seen%20from%20the%20Mars%20Global%20Surveyor%20spacecraft.jpg"));
        products.add(new Product(cats.get(6),"Jupiter","Jupiter",1.45,base_url+"space/small/Jupiter.jpg",base_url+"space/medium/Jupiter.jpg"));
        products.add(new Product(cats.get(6),"Mercury","Mercury",1.45,base_url+"space/small/Mercury.jpg",base_url+"space/medium/Mercury.jpg"));
        products.add(new Product(cats.get(6),"Neptune","Neptune",0.45,base_url+"space/small/Neptune.jpg",base_url+"space/medium/Neptune.jpg"));
        products.add(new Product(cats.get(6),"Pluto","Pluto",1.45,base_url+"space/small/Pluto.jpg",base_url+"space/medium/Pluto.jpg"));
        products.add(new Product(cats.get(6),"Saturn","Saturn",1.45,base_url+"space/small/Saturn.jpg",base_url+"space/medium/Saturn.jpg"));
        products.add(new Product(cats.get(6),"Uranus as seen by Voyager 2","Uranus as seen by Voyager 2",0.95,base_url+"space/small/Uranus%20as%20seen%20by%20Voyager%202.jpg",base_url+"space/medium/Uranus%20as%20seen%20by%20Voyager%202.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",2.45,base_url+"sun/small/1.jpg",base_url+"sun/medium/1.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/10.jpg",base_url+"sun/medium/10.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/11.jpg",base_url+"sun/medium/11.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/12.jpg",base_url+"sun/medium/12.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",1.45,base_url+"sun/small/13.jpg",base_url+"sun/medium/13.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/14.jpg",base_url+"sun/medium/14.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",0.45,base_url+"sun/small/15.jpg",base_url+"sun/medium/15.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",0.45,base_url+"sun/small/16.jpg",base_url+"sun/medium/16.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/17.jpg",base_url+"sun/medium/17.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",1.45,base_url+"sun/small/18.jpg",base_url+"sun/medium/18.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/19.jpg",base_url+"sun/medium/19.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",1.45,base_url+"sun/small/2.jpg",base_url+"sun/medium/2.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",1.45,base_url+"sun/small/20.jpg",base_url+"sun/medium/20.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/3.jpg",base_url+"sun/medium/3.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",2.45,base_url+"sun/small/4.jpg",base_url+"sun/medium/4.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/5.jpg",base_url+"sun/medium/5.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/6.jpg",base_url+"sun/medium/6.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",1.95,base_url+"sun/small/7.jpg",base_url+"sun/medium/7.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",0.45,base_url+"sun/small/8.jpg",base_url+"sun/medium/8.jpg"));
        products.add(new Product(cats.get(7),"sun picture","sun picture",0.95,base_url+"sun/small/9.jpg",base_url+"sun/medium/9.jpg"));
        for (Product product: products) {
            String genericDesc = product.getCategory().getName() + " picture";
            product.setName(genericDesc);
            product.setDescription(genericDesc);
        }
        prodRepo.saveAll(products);

        // Populate table "product_review"
        List<ProductReview> reviews = new ArrayList<>();
        for (Product product: products) { // generate some reviews for each product
            int reviewsToGenerate = ThreadLocalRandom.current().nextInt(2, 10+1); // 10+1 is intentional
            // amount of reviews for each product could be as few as 2 reviews or as many as 10;

            int catId = -1; // -1 is set so script won't execute without a category being selected
            for (int j=0; j<defaultCategories.length; j++) { // determine category of the product
                if (defaultCategories[j].equalsIgnoreCase(product.getCategory().getName())) {
                    catId = j;
                    break;
                }
            }
            int maxLen = data.getReviews()[catId].length; // maxLen is the amount of reviews available for category catId
            for (int i=0; i<reviewsToGenerate; i++) { // generates a review reviewsToGenerate number of times
                int rating = ThreadLocalRandom.current().nextInt(0, 5+1); // random rating between 0 and 5 inclusive
                String desc = data.getReviews()[catId][ThreadLocalRandom.current().nextInt(0, maxLen)];
                // randomly grab a textual rating from those available for the category

                int userId = ThreadLocalRandom.current().nextInt(0, users.size()); // review assigned to random user
                reviews.add(new ProductReview(rating,desc, users.get(userId),product)); // review persisted
            }
        }
        reviewRepo.saveAll(reviews);

        // Populates "Address" table; this code was generated in Python using the random_address package
        List<Address> addresses = new ArrayList<>(); // handy list for use here
        for (String[] address: data.getAddresses()) {
            addresses.add(new Address(address[0],address[1],address[2],address[3],address[4]));
        }
        addressRepo.saveAll(addresses);

        // Populate table "order"
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 50; i++) { // 50 random orders
            int userInd = ThreadLocalRandom.current().nextInt(0, users.size()); // randomly get a user
            User user = users.get(userInd);
            int addressInd = ThreadLocalRandom.current().nextInt(0, addresses.size()); // randomly get an address
            Address address = addresses.get(addressInd);
            OrderStatus status;
            switch(ThreadLocalRandom.current().nextInt(0, 6)) { // randomly assign order status
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
            // randomly determine how many items were ordered

            List<Product> cart = new ArrayList<>();
            List<Integer> prodInds = new ArrayList<>(); // list of indexes
            for (int j = 0; j < amountOfItems; j++) {
                int prodId = ThreadLocalRandom.current().nextInt(0, products.size()); // randomly select product
                if(!prodInds.contains(prodId)) { // this prevents duplicates
                    prodInds.add(prodId); // keeps track of what indexes have been selected
                    cart.add(products.get(prodId));
                }
            }
            orders.add(new Order(user,address,status,cart));
        }
        orderRepo.saveAll(orders);

        long totalTime = System.nanoTime() - startTime;
        System.out.println("Mock data insertion took " + (totalTime/Math.pow(1000,3)) + " seconds");
    }

    private static void stars() { // for debugging; prints 100 asterisks
        for (int i = 0; i < 10; i++) {
            System.out.print("**********");
        }
        System.out.println();
    }
}
