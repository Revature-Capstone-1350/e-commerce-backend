package com.revature.config;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Data {
    private final String[][] reviews;
    private final String[] names;
    private final String[] emails;

    private static final String genNames  ="Thomas Stewart\n" +
            "Rebekah Harpin\n" +
            "Thomas Page\n" +
            "Wanda Maynard\n" +
            "Amber Elias\n" +
            "Hugh Dugan\n" +
            "Linda Kelley\n" +
            "William Quinones\n" +
            "Marjorie Bunker\n" +
            "Charles Cullen\n" +
            "Lester Campanile\n" +
            "Katie Moser\n" +
            "Jay Marshall\n" +
            "Kelly Johnson\n" +
            "Helen Smith\n" +
            "Bernadette Martinez\n" +
            "Ruth Collier\n" +
            "Jeremiah Miller\n" +
            "Erica Glass\n" +
            "Julienne Eike\n" +
            "Jenine Graves\n" +
            "Marie Douglas\n" +
            "Lawrence Thomas\n" +
            "Donald Johnson\n" +
            "James Kamal\n" +
            "Viola Anaya\n" +
            "Elizabeth Osler\n" +
            "John Sears\n" +
            "Shantay Robinson\n" +
            "Melinda Surano\n" +
            "Lois Burwell\n" +
            "Ronald Crandall\n" +
            "Gladys Simmons\n" +
            "Carl Rivers\n" +
            "Mary Jacoby\n" +
            "Guadalupe Hartley\n" +
            "Raymond Perry\n" +
            "Donna Felton\n" +
            "Becky Howerton\n" +
            "Barbara Escovedo\n" +
            "August Koonce\n" +
            "Frankie Welty\n" +
            "Rosemary Henshaw\n" +
            "Julia Washington\n" +
            "Mavis Nelson\n" +
            "Kristy Huneycutt\n" +
            "Mario Castrellon\n" +
            "Cornelius Brodie\n" +
            "Walter Dickerson\n" +
            "Charlotte Hartman\n" +
            "Rebecca Monroe\n" +
            "Elva Sloan\n" +
            "Robert Pena\n" +
            "Joshua Brooks\n" +
            "Alexandra Richey\n" +
            "Thomas Savage\n" +
            "Robert Adam\n" +
            "Charles Hofmann\n" +
            "Manuel Mattison\n" +
            "Billy Bonilla\n" +
            "Michael Perry\n" +
            "William Ordonez\n" +
            "Robert Hales\n" +
            "Garry Kahrs\n" +
            "James Ramos\n" +
            "Daniel Searcy\n" +
            "Leon Moulton\n" +
            "Tina Levy\n" +
            "Becky Knox\n" +
            "Tiffany Lee\n" +
            "Gilbert Schut\n" +
            "Patricia Hill\n" +
            "Dawn Berube\n" +
            "Dana Loomis\n" +
            "Maria Fowler\n" +
            "Anthony Fletcher\n" +
            "Tom Sahsman\n" +
            "Eric Searles\n" +
            "Thomas Whitely\n" +
            "Juliana Brothern\n" +
            "Rocio Palmer\n" +
            "Charles Escobar\n" +
            "Stephen Hawkins\n" +
            "Amanda Moats\n" +
            "Ernest Watts\n" +
            "Samuel Bennett\n" +
            "Barbara Orcutt\n" +
            "Darius Peachey\n" +
            "Ray Oldaker\n" +
            "Hector Chew\n" +
            "Paul Doyle\n" +
            "Margaret Boyle\n" +
            "Dan Harold\n" +
            "Martin Fitzgerald\n" +
            "Larry Allen\n" +
            "Patrick Molinaro\n" +
            "John Buckner\n" +
            "Tonya Pierce\n" +
            "Robert Halstead\n" +
            "Alison Pennel";
    private static final String genEmails = "yumpy@live.com\n" +
            "rhavyn@mac.com\n" +
            "xtang@msn.com\n" +
            "mpiotr@yahoo.ca\n" +
            "pjacklam@att.net\n" +
            "bryanw@verizon.net\n" +
            "goldberg@yahoo.ca\n" +
            "aprakash@sbcglobal.net\n" +
            "milton@msn.com\n" +
            "atmarks@outlook.com\n" +
            "webteam@gmail.com\n" +
            "joglo@msn.com\n" +
            "dawnsong@att.net\n" +
            "jkegl@att.net\n" +
            "chaffar@yahoo.ca\n" +
            "jfriedl@live.com\n" +
            "nachbaur@live.com\n" +
            "panolex@yahoo.ca\n" +
            "fraterk@icloud.com\n" +
            "kingjoshi@optonline.net\n" +
            "wonderkid@icloud.com\n" +
            "nullchar@aol.com\n" +
            "schwaang@att.net\n" +
            "leocharre@outlook.com\n" +
            "gastown@att.net\n" +
            "nichoj@yahoo.com\n" +
            "goresky@outlook.com\n" +
            "hamilton@optonline.net\n" +
            "bester@comcast.net\n" +
            "mwilson@icloud.com\n" +
            "fangorn@aol.com\n" +
            "hermanab@comcast.net\n" +
            "kmself@yahoo.ca\n" +
            "ismail@me.com\n" +
            "rwelty@me.com\n" +
            "dougj@verizon.net\n" +
            "nullchar@sbcglobal.net\n" +
            "nick@optonline.net\n" +
            "sbmrjbr@gmail.com\n" +
            "amcuri@yahoo.ca\n" +
            "gregh@yahoo.com\n" +
            "pierce@yahoo.com\n" +
            "enintend@gmail.com\n" +
            "tarreau@mac.com\n" +
            "seano@yahoo.com\n" +
            "clkao@att.net\n" +
            "nichoj@gmail.com\n" +
            "jramio@aol.com\n" +
            "mbrown@icloud.com\n" +
            "gemmell@icloud.com\n" +
            "hauma@me.com\n" +
            "earmstro@comcast.net\n" +
            "kudra@sbcglobal.net\n" +
            "markjugg@mac.com\n" +
            "matthijs@yahoo.ca\n" +
            "damian@live.com\n" +
            "webinc@hotmail.com\n" +
            "unreal@aol.com\n" +
            "lahvak@gmail.com\n" +
            "burns@icloud.com\n" +
            "marin@icloud.com\n" +
            "caidaperl@optonline.net\n" +
            "starstuff@outlook.com\n" +
            "jdray@icloud.com\n" +
            "shazow@hotmail.com\n" +
            "jacks@comcast.net\n" +
            "kidehen@gmail.com\n" +
            "stern@aol.com\n" +
            "grady@mac.com\n" +
            "hoangle@gmail.com\n" +
            "irving@icloud.com\n" +
            "nighthawk@hotmail.com\n" +
            "satch@me.com\n" +
            "penna@optonline.net\n" +
            "denton@verizon.net\n" +
            "lauronen@msn.com\n" +
            "adhere@aol.com\n" +
            "jyoliver@att.net\n" +
            "rohitm@mac.com\n" +
            "kspiteri@optonline.net\n" +
            "kingjoshi@me.com\n" +
            "zwood@icloud.com\n" +
            "mpiotr@hotmail.com\n" +
            "fhirsch@yahoo.com\n" +
            "jgmyers@yahoo.com\n" +
            "goresky@optonline.net\n" +
            "jbearp@icloud.com\n" +
            "loscar@me.com\n" +
            "kjohnson@hotmail.com\n" +
            "novanet@sbcglobal.net\n" +
            "arnold@gmail.com\n" +
            "gilmoure@att.net\n" +
            "emmanuel@aol.com\n" +
            "kudra@yahoo.ca\n" +
            "trieuvan@yahoo.com\n" +
            "dsowsy@aol.com\n" +
            "dmath@optonline.net\n" +
            "koudas@optonline.net\n" +
            "carcus@verizon.net\n" +
            "mcnihil@outlook.com";
    private static final String[] genReviews = {"talk about surprise!!!\n" +
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
            "My co-worker Ali has one of these. He says it looks towering.",
            "heard about this on original pilipino music radio, decided to give it a try.\n" +
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
            "This Dawn works so well. It delightedly improves my football by a lot.",
            "I tried to scratch it but got cheeseburger all over it.\n" +
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
            "this Day picture is whole-grain.",
            "My neighbor Julisa has one of these. She works as a bartender and she says it looks crooked.\n" +
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
            "this Dusk snap is honest.",
            "The box this comes in is 5 yard by 6 centimeter and weights 12 kilogram.\n" +
            "My neighbor Georgie has one of these. She works as a busboy and she says it looks brown.\n" +
            "My neighbor Aldona has one of these. She works as a butler and she says it looks humongous.\n" +
            "this Moon is tasty.\n" +
            "My neighbor Montserrat has one of these. She works as a circus performer and she says it looks shriveled.\n" +
            "talk about jank!!\n" +
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
            "this Moon is honest.",
            "The box this comes in is 3 light-year by 5 meter and weights 10 ounce!\n" +
            "this Night pic is dominant.\n" +
            "talk about jank!!\n" +
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
            "talk about jank!!\n" +
            "one of my hobbies is drawing. and when i'm drawing this works great.\n" +
            "My co-worker Archer has one of these. He says it looks crooked.\n" +
            "This Night pic works considerably well. It secretly improves my basketball by a lot.\n" +
            "I saw one of these in Kazakhstan and I bought one.\n" +
            "one of my hobbies is mushroom cultivation. and when i'm cultivating mushrooms this works great.\n" +
            "talk about contempt!!!\n" +
            "heard about this on timba radio, decided to give it a try.\n" +
            "i use it never when i'm in my nightclub.\n" +
            "this Night pic is flirty.",
            "I tried to slay it but got truffle all over it.\n" +
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
            "I tried to vomit it but got bonbon all over it.",
            "SoCal cockroaches are unwelcome, crafty, and tenacious. This Sun pic keeps them away.\n" +
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
            "i use it never when i'm in my nightclub."
    };

    public Data() { // no-args constructor
        String[][] reviewBuilder = new String[genReviews.length][genReviews[0].split("\n").length]; // [0] used because all equal length
        // 2-D array with each category representing the first index, and category-specific reviews the second index

        int ind = 0;
        for (String raw: genReviews) {
            reviewBuilder[ind] = raw.split("\n"); // populate 2nd index of array by category
            ind++;
        }
        this.reviews = reviewBuilder; // sets this.reviews to the 2-D array that separates reviews by category
        this.names = genNames.split("\n"); // sets this.names to an array with each entry like "first last"
        String[] emailBuilder = genEmails.split("\n"); // this becomes an array with each entry like "abc@domain.com"

        for (int i=0; i < emailBuilder.length; i++ ) { // change 40% of the emails to first.last@domain.com
            if(ThreadLocalRandom.current().nextInt(0, 10) < 4) {
                emailBuilder[i] = this.names[i].replace(" ",".") +
                        "@" + emailBuilder[i].split("@")[1];
            }
        }
        this.emails = emailBuilder; // sets this.emails to the final list of emails
    }

    // Getters
    public String[][] getReviews() { return reviews; }
    public String[] getNames() { return names; }
    public String[] getEmails() { return emails; }
}
