package com.example.springblog.components;

import com.example.springblog.models.Category;
import com.example.springblog.models.Product;
import com.example.springblog.models.User;
import com.example.springblog.models.UserRole;
import com.example.springblog.repo.CategoryRepository;
import com.example.springblog.repo.ProductRepository;
import com.example.springblog.repo.UserRepository;
import com.example.springblog.repo.UserRoleRepository;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private final UserRoleRepository roleRepo;
  private final CategoryRepository catRepo;
  private final ProductRepository prodRepo;
  private final UserRepository userRepo;
  private final PasswordEncoder passwordEncoder;

  @Value("${app.env}")
  private String environment;

  @Autowired
  public DatabaseSeeder(UserRoleRepository roleRepo,
      CategoryRepository catRepo, ProductRepository prodRepo,
      UserRepository userRepo, PasswordEncoder passwordEncoder) {
    this.roleRepo = roleRepo;
    this.catRepo = catRepo;
    this.prodRepo = prodRepo;
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
  }

  private void seedRoles() {
      UserRole roles[] = {
          new UserRole("ROLE_ADMIN",1),
          new UserRole("ROLE_DRIVER", 2),
          new UserRole("ROLE_CUSTOMER", 3)
      };
      roleRepo.save(Arrays.asList(roles));
  }

  private void seedCategories() {
      Category types[] = {
          new Category("beer"),
          new Category("wine"),
          new Category("liquor")
      };
      catRepo.save(Arrays.asList(types));
  }

  private void seedUsers() {
      User users[] = {
          new User("admin", "admin@gmail.com", passwordEncoder.encode("password")),
          new User("driver", "driver@gmail.com", passwordEncoder.encode("password")),
          new User("customer", "customer@gmail.com", passwordEncoder.encode("password"))
      };
      userRepo.save(Arrays.asList(users));
  }

  private void seedProducts() {
    Product products[] = {
      new Product(
          "Samuel Adams Boston Lager",
          "Amber / Vienna Lager",
          "This is the beer that"
              + " started it all. Samuel Adams Boston Lager helped lead the American beer revolution, reviving a passion for full-flavored brews that are robust and rich with character.  Since 1984, Samuel Adams Boston Lager has used only the finest hand-selected ingredients to create this perfectly balanced and complex original brew.",
          catRepo.findByName("beer"),
          userRepo.findByUsername("admin"), 59.79),
      new Product(
          "He'Brew Funky Jewbelation",
          "Wild Ale",
          "A blend of 6 ales aged in 73% rye"
              + " whiskey and 27% bourbon barrels. The blend consists of He'Brew Jewbelation 15, Vintage Jewbelation, Bittersweet Lenny's RIPA, He'Brew Origin, Reunion Ale '11, & He'Brew Messiah.",
          catRepo.findByName("beer"),
          userRepo.findByUsername("admin"), 26.99),
      new Product(
          "New Belgium Fat Tire Belgian Style Ale",
          "Belgian-Style Ale",
          "New Belgium's most well-known beer is Fat Tire Amber Ale. It's a clear, coppery-colored amber ale that's smooth and easy to drink. Fat Tire was inspired by a co-founder’s bicycle trip around Belgium, and the recipe incorporates the sweet aromas and flavors of his travels—toasty, caramel malts; fresh fennel, clean green apple; and sweet biscuits. If you enjoy smooth, medium-bodied beers, this ubiquitous craft beer is a tasty one to add to your reserve.\n"
              + "In the craft brewing scene, Colorado-based New Belgium Brewing Company is quite the success story. Before 2002, you could only find its beers in 16 states; today, they are the country’s third largest craft brewery.",
          catRepo.findByName("beer"),
          userRepo.findByUsername("admin"), 59.99),
      new Product(
          "Stella Artois",
          "Pilsner",
          "Enjoy the European way with the #1 best-selling Belgian beer in the world. With its wonderful floral aroma, well-balanced malt sweetness, crisp hop bitterness and soft dry finish, Stella Artois is the perfect beer to pair with food and friends. Serve in its signature glass chalice to enhance flavor and aroma. Brewed in Belgium. Pairs well with steak, mussels, and chocolate desserts. 5% alcohol by volume.",
          catRepo.findByName("beer"),
          userRepo.findByUsername("admin"), 59.99),
      new Product(
          "Guinness Draught",
          "Dry Stout",
          "Guinness has a sweet aroma redolent of roasted malts, faint caramel and light smoke. The roasted character comes through fully on the palate and accompanies notes of coffee, chocolate and mild smoke. Although remaining light in body, Guinness has a creamy, silky smooth mouthfeel that carries this roasted essence all the way to the finish. ABV 4.20\n"
              + "\n",
          catRepo.findByName("beer"),
          userRepo.findByUsername("admin"), 59.99),
      new Product(
          "Lord Hobo Boom Sauce IPA",
          "IPA",
          "Our flagship IPA features six hop varietals and a blend of spelt, oat and wheat. A late hop addition of Mosaic, Falconer’s Flight and Amarillo delivers a notable citrus and tropical fruit finish.",
          catRepo.findByName("beer"),
          userRepo.findByUsername("admin"), 34.99),
      new Product(
          "Keystone Light",
          "Light Lager",
          "Keystone Light is a natural light, crisp, refreshing American Light Lager that has well-balanced flavor with a malty note, low bitterness and light body. This easy to drink beer is ideal for downtime at home or fun with friends; Light & Refreshing with 4.1% ABV.\n"
              + "Keystone Light was created in 1989 by Coors Brewing Company. For over 25 years we've been brewing a refreshing light beer that that’s always smooth. Our light lager is available in a variety of packages including beer Cans and beer Bottles to fit any occasion. The 30 pack is perfect for Summer beach day cookouts, over the 4th of July holiday, evenings on the front porch and a bonfire party. The 24 pack is the ideal Fall beer for hunting or tailgating. The 6 pack is the perfect Spring beer for a barbeque. And the 15 pack is ideal to keep at home for the next spontaneous garage party. Keystone Light is also the perfect choice for holidays including 21st birthday parties, labor day, St. Patrick's day, march madness, memorial day and fourth of July.",
          catRepo.findByName("beer"),
          userRepo.findByUsername("admin"), 29.99),
      new Product(
          "Excelsior Cabernet Sauvignon",
          "Cabernet Sauvignon",
          "This delicious, crowd pleasing Cabernet – consistently named a Wine Enthusiast \"Best Buy\" – delivers serious bang for the buck! It's packed with lip smacking flavors of blackcurrant, black cherry and plum, with subtle hints of dark chocolate and spice.",
          catRepo.findByName("wine"),
          userRepo.findByUsername("admin"), 14.99),
      new Product(
          "Dark Horse Cabernet Sauvignon",
          "Cabernet Sauvignon",
          "The New World Cab that just can't stop exceeding expectations. This full-throttle bottle is a true Dark Horse original. Bringing together a carefully selected collection of California grapes, winemaker, Beth Liston has found a way to balance big fruit flavors with deep, dry, mouthwatering notes. An instant hit, our Cab loves any party or holiday gathering where there’s a chance to mingle with all kinds of tastes.",
          catRepo.findByName("wine"),
          userRepo.findByUsername("admin"), 15.99),
      new Product(
          "Freakshow Cabernet Sauvignon",
          "Cabernet Sauvignon",
          "Known for taking the road less traveled, Michael David Winery has always stood apart from the crowd. Whether it be their outlandish brands and packaging or their quirky personalities, Michael and David are definitely on a stage of their own and this wine showcases just that.   This wine is FREAKing amazing! Over each vintage, the intensity has been kicked up exponentially. This 4th incarnation has more of everything…more depth, more ripe fruit, more pizazz! Nicely balanced with fruit (pomegranate) and oak (showing some smoke). Warning—it’s gulpable!\n",
          catRepo.findByName("wine"),
          userRepo.findByUsername("admin"), 35.09),
      new Product(
          "Educated Guess Napa Valley Cabernet Sauvignon",
          "Cabernet Sauvignon",
          "A rich, ripe and focused Cabernet Sauvignon with juicy blackberry and cherry fruit, cocoa and hints of mint all tied together with a creamy french vanilla middle and a finish that in a word lingering.",
          catRepo.findByName("wine"),
          userRepo.findByUsername("admin"), 32.99),
      new Product(
          "Kim Crawford Sauvignon Blanc",
          "Sauvignon Blanc",
          "Kim Crawford Sauvignon Blanc is an exuberant New Zealand wine brimming with fruity sweetness and juicy acidity for a balanced flavor profile that wine connoisseurs will adore. The finish is zesty and fresh, lingering on the tongue with its citrusy aromas and characteristic herbaceous notes. The long-established Wairau Valley and its neighboring Awatere Valley are situated at the northeastern tip of the South Island, a dry, sunny region that produces most of New Zealand's wine. This region features fast-draining loams that are ideal for growing grapes. In fact, the area is particularly famous for its zesty, pungent Marlborough Sauvignon Blanc. Vines are varied in age, bringing a mix of youthful vigor and a mature depth of flavor to each bottle of Kim Crawford Sauvignon Blanc. It's no wonder the wine has garnered high marks from some of the world's most important wine critics.\n"
              + "\n"
              + "Expect a light straw color with yellow and green hues to match the herbaceous and tropical qualities of the wine. Each sip brings forth a plethora of fruit flavors, including apricot, nectarine, passion fruit and honeysuckle. Best of all, the wine continues to develop from one to three years after bottling, only getting better with age. As such, it's a wonderful wine to drink anytime with friends or simply when you want to reward yourself after a long day. Pair it with asparagus, fresh oysters, summer salads or lobster to highlight the flavors and savor an unbeatable experience.",
          catRepo.findByName("wine"),
          userRepo.findByUsername("admin"), 32.99),
      new Product(
          "The Pinot Project Pinot Noir",
          "Pinot Noir",
          "Mission accomplished! The Pinot Project has accomplished its goal of being one of the greatest red wine values coming out of California. It is hand-crafted from grapes grown in notable AVA's such as Sonoma County, Carneros & Monterey. The Pinot Project has a full and silky mouth feel, with just the right amount of acidity to complement a variety of dishes. The wine is 100% stainless steel fermented with the caps receiving periodic punchdowns, before finishing dry with just a kiss of oak.",
          catRepo.findByName("wine"),
          userRepo.findByUsername("admin"), 19.99),
      new Product(
          "Jameson Irish Whiskey",
          "Irish Whiskey",
          "Perhaps the most notable whiskey on earth, Jameson Irish Whiskey is a crisp, sippable drink that has stood the test of time. Offering hints of vanilla, cream and freshly cut grass with a touch of sweetness, Jameson Whiskey is aged for a minimum of four years, resulting in the quintessential triple-distilled liquid that is both smooth and versatile. Simply put, it's the stuff that turned Jameson's green bottle into an icon, making it the brand we all know and love today.\n"
              + "\n"
              + "Jameson Irish Whiskey boasts a perfectly balanced flavor profile with spicy, nutty and sweet notes. The nose is lightly floral and peppered with spice, making this one of the most broadly appealing whiskeys on the market. Enjoy it straight, on the rocks, with a bit of water or mixed with club soda, ginger ale or in cocktails. With Jameson's classic concoction, the possibilities for enjoying a true Irish tradition are endless!",
          catRepo.findByName("liquor"),
          userRepo.findByUsername("admin"), 51.99),
      new Product(
          "Talisker Game of Thrones House Greyjoy Select Reserve Single Malt Scotch Whisky",
          "Scotch Whisky",
          "House Greyjoy rules the Iron Islands and worships the Drowned God. Talisker was a natural pair for House Greyjoy as this single malt is distilled on the shores of the Isle of Skye, one of the most remote and rugged areas of Scotland. The layered flavors and signature maritime character of Talisker Select Reserve are the result of its wave-battered shores. \n"
              + "\n"
              + "This liquid is an intense smoky single malt Scotch with spicy, powerful and sweet elements combined with maritime flavors. Dry Sichuan peppery smoke and dark chocolate on the nose. On the palate the salted caramel with chili flakes explode into a smoky fruit cake of spice and the finish is long and complex with a lasting character of leather. These iconic whiskies serve as must-have collectibles for Game of Thrones and whisky adorers. Please drink responsibly.",
          catRepo.findByName("liquor"),
          userRepo.findByUsername("admin"), 999.99),
      new Product(
          "Dalwhinnie Game of Thrones House Stark Winter's Frost Highland Single Malt Scotch Whisky",
          "Scotch Whisky",
          "House Stark’s resiliency, strength and ability to thrive under the most intense situations are greatly shaped by Winterfell’s frigid temperatures. Dalwhinnie, known for being one of the highest distilleries in all of Scotland is cold and remote much like The North where House Stark calls home, making the two an iconic pairing.  \n"
              + "\n"
              + "Extreme conditions are responsible for shaping the signature Dalwhinnie Winter’s Frost honeyed sweetness and spicy warmth. Naturally, it’s best served chilled or over ice.  Tastes of honey and fresh fig develop on the palate followed by a delicious maltiness and rich fruit cake. These iconic whiskies serve as must-have collectibles for Game of Thrones and whisky adorers.",
          catRepo.findByName("liquor"),
          userRepo.findByUsername("admin"), 999.99),
      new Product(
          "Don Julio Blanco",
          "Silver / Blanco Tequila",
          "Using the finest blue agave plant and a time honored distillation process, Don Julio Blanco is tequila in its truest form. Double-distilled agave to achieve a clean and dry finish. Blanco is the base of all Don Julio aged tequilas. Gluten-free & Kosher. Sip it neat or in a Don Julio Luxury margarita.",
          catRepo.findByName("liquor"),
          userRepo.findByUsername("admin"), 122.99),
      new Product(
          "Tito's Handmade Vodka",
          "Vodka",
          "Tito's Handmade Vodka is designed to be savored by spirit connoisseurs.  It is micro-distilled in an old-fashioned pot still, just like fine single malt scotches and high-end French cognacs.  This time-honored method of distillation requires more skill and effort than modern column stills, but it's well worth it. Our handcrafted technique offers more  control over the distillation process, resulting in a spectacularly clean product of incomparable excellence.  Only the heart of the run, \"the nectar\" is taken, leaving behind residual higher and lower alcohols.  The vodka is cleansed of phenols, esters, congenersand organic acids by filtering it through the finest activated carbon available.  Critics call Tito's \"a homegrown symphonic spirit to applaud!\" and say \"it can go head to head with any of the worlds' greats and not break a sweat.",
          catRepo.findByName("liquor"),
          userRepo.findByUsername("admin"), 37.09)
    };
      prodRepo.save(Arrays.asList(products));
  }

  @Override
  public void run(String... strings) throws Exception {
    if (!environment.equals("development")) {
      log.info("app.env is not development, doing nothing.");
      return;
    }
    log.info("Deleting products...");
    prodRepo.deleteAll();
    log.info("Deleting users...");
    userRepo.deleteAll();
    log.info("Deleting categories...");
    catRepo.deleteAll();
    log.info("Deleting roles...");
    roleRepo.deleteAll();
    log.info("Seeding roles...");
    seedRoles();
    log.info("Seeding categories...");
    seedCategories();
    log.info("Seeding users...");
    seedUsers();
    log.info("Seeding products...");
    seedProducts();
    log.info("Finished running seeders!");
  }

}
