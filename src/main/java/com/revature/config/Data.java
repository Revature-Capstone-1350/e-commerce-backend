package com.revature.config;

import java.util.concurrent.ThreadLocalRandom;

public class Data {
    private final String[][] reviews;
    private final String[] names;
    private final String[] emails;
    private final String[][] addresses;

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
        String[][] addressGen = {{"4 Chamois Court","","Pooler","GA","31322"},
                {"295 Townes Drive","","Nashville","TN","37211"},
                {"5161 Jefferson Boulevard","#202","Louisville","KY","40219"},
                {"429 Dennison Ridge Drive","","Manchester","CT","06040"},
                {"4438 Maine Avenue","","Baldwin Park","CA","91706"},
                {"510 Leeanne Drive","","Nashville","TN","37211"},
                {"5461 West Shades Valley Drive","","Montgomery","AL","36108"},
                {"2032 Gorgas Street","","Montgomery","AL","36106"},
                {"707 Leaning Oaks Drive","","Savannah","GA","31410"},
                {"7 Oliver Street","#2","Athol","MA","01331"},
                {"111 Edgewater Road","#115","Savannah","GA","31406"},
                {"16 Cabot Street","#2","Everett","MA","02149"},
                {"904 Walthour Road","","Savannah","GA","31410"},
                {"2248 South Sutherland Drive","","Montgomery","AL","36116"},
                {"1002 Cedar Ridge Court","","Annapolis","MD","21403"},
                {"31130 Meadowbrook Avenue","","Hayward","CA","94544"},
                {"706 James Road","","Glen Burnie","MD","21061"},
                {"8125 Glynnwood Drive","","Montgomery","AL","36117"},
                {"115 Saint John Street","","Manchester","CT","06040"},
                {"3031 Casa Drive","","Nashville","TN","37214"},
                {"2504 Longest Avenue","","Louisville","KY","40204"},
                {"840 Fontaine Road","","Derby","VT","05829"},
                {"4696 Bull Run Road","","Ashland City","TN","37015"},
                {"26 Garfield Street","","Bristol","VT","05443"},
                {"82 Linnmore Drive","","Manchester","CT","06040"},
                {"7549 Delancey Street","","Youngstown","FL","32466"},
                {"165 New Hampshire Avenue","","Somerset","MA","02726"},
                {"7024 Johnny Mercer Boulevard","","Savannah","GA","31410"},
                {"488 Mill Road","","Hartford","VT","05001"},
                {"2703 Woolsey Street","#R 1","Berkeley","CA","94705"},
                {"82 East Foster Street","#2","Melrose","MA","02176"},
                {"5102 Ander Drive","","Brentwood","TN","37027"},
                {"131 Kent Drive","","Manchester","CT","06042"},
                {"3408 Bellisima Place","#204","Louisville","KY","40245"},
                {"6391 West 60th Avenue","#210","Arvada","CO","80003"},
                {"29104 Quartz Lane","","Tollhouse","CA","93667"},
                {"217 West Congress Street","","Savannah","GA","31401"},
                {"1806 Boscobel Street","","Nashville","TN","37206"},
                {"2410 Pafford Drive","","Nashville","TN","37206"},
                {"113 Hammarlee Road","","Glen Burnie","MD","21060"},
                {"13931 West 87th Drive","","Arvada","CO","80005"},
                {"10841 Sutter Circle","","Sutter Creek","CA","95685"},
                {"2433 Southwest 36th Street","","Oklahoma City","OK","73109"},
                {"3551 North Georgetown Drive","","Montgomery","AL","36109"},
                {"2619 North Quality Lane","#315","Fayetteville","AR","72703"},
                {"7404 West Crest Lane","","Glendale","AZ","85310"},
                {"5921 Ashwood Bluff Drive","","Louisville","KY","40207"},
                {"324 Martin Luther King Junior Boulevard","","Fayetteville","AR","72701"},
                {"82 The Commons","","Moretown","VT","05660"},
                {"18713 Shilstone Way","","Edmond","OK","73012"},
                {"7952 South Algonquian Way","","Aurora","CO","80016"},
                {"10301 La Plaza Drive","","Louisville","KY","40272"},
                {"2560 Michigan Court","","Panama City","FL","32405"},
                {"201 North Locust Avenue","B","Fayetteville","AR","72701"},
                {"152 Holly Court","","Mountain View","CA","94043"},
                {"370 Wallace Road","#D-28","Nashville","TN","37211"},
                {"408 Pine Street","","Bloomingdale","GA","31302"},
                {"1995 Nolensville Pike","","Nashville","TN","37211"},
                {"505 Southeast 32nd Street","","Oklahoma City","OK","73129"},
                {"265 Airport Road","","Weathersfield","VT","05151"},
                {"12 Winter Street","","Manchester","CT","06040"},
                {"6620 North 61st Drive","","Glendale","AZ","85301"},
                {"1189 Northwest End Avenue","#D3","Fayetteville","AR","72703"},
                {"704 Crescent Road","","Nashville","TN","37205"},
                {"3301 Nora Lane","","Louisville","KY","40220"},
                {"3808 South Smiley Circle","","Montgomery","AL","36108"},
                {"400 South 28th Street","","Louisville","KY","40212"},
                {"816 East 69th Street","","Savannah","GA","31405"},
                {"3420 Horseshoe Circle","","Montgomery","AL","36116"},
                {"3466 Southview Avenue","","Montgomery","AL","36111"},
                {"88 Pine Valley Road","","Savannah","GA","31404"},
                {"36 Grove Street","F","Manchester","CT","06042"},
                {"327 Idlewylde Drive","#3","Louisville","KY","40206"},
                {"1751 Shoreham Drive","","Montgomery","AL","36106"},
                {"8010 Holland Court","D","Arvada","CO","80005"},
                {"207 Maple Hill Road","","Shaftsbury","VT","05262"},
                {"12 Fletcher Lane","","Shelburne","VT","05482"},
                {"2220 Kirk Avenue","","Nashville","TN","37218"},
                {"2708 Mabel Street","M","Berkeley","CA","94702"},
                {"12013 Blue Moon Avenue","","Oklahoma City","OK","73162"},
                {"7758 Betty Louise Drive","","Panama City","FL","32404"},
                {"9222 Sandra Grace Road","","Southport","FL","32409"},
                {"2572 Drake Street","","Montgomery","AL","36108"},
                {"104 Bennington Street","","Lawrence","MA","01841"},
                {"5950 West Missouri Avenue","#148","Glendale","AZ","85301"},
                {"129 Lark Lane","","Farmington","AR","72730"},
                {"190 High Street","#213C","Medford","MA","02155"},
                {"4738 Mallard Common","","Fremont","CA","94555"},
                {"2021 West Burnett Avenue","","Louisville","KY","40210"},
                {"102 Derondo Street","","Panama City Beach","FL","32413"},
                {"5606 Olde Wadsworth Boulevard","#20","Arvada","CO","80002"},
                {"5747 West Missouri Avenue","#122","Glendale","AZ","85301"},
                {"168 Partridge Hill Road","","Charlton","MA","01507"},
                {"243 Kentucky Avenue","","Pasadena","MD","21122"},
                {"13066 North 56th Avenue","","Glendale","AZ","85304"},
                {"3110 East Victory Drive","","Savannah","GA","31404"},
                {"1842 West Park Place","","Oklahoma City","OK","73106"},
                {"64 Roseberry Circle","","Port Wentworth","GA","31407"},
                {"11113 North Miller Avenue","","Oklahoma City","OK","73120"},
                {"143 Canton Court","","Goodlettsville","TN","37072"},
                {"3528 Seasons Drive","","Nashville","TN","37013"},
                {"2728 Hale Avenue","","Louisville","KY","40211"},
                {"204 Blue Hills Drive","","Nashville","TN","37214"},
                {"10301 La Plaza Drive","","Louisville","KY","40272"},
                {"7419 West Hill Lane","","Glendale","AZ","85310"},
                {"9545 West 74th Avenue","","Arvada","CO","80005"},
                {"416 South University Boulevard","","Norman","OK","73069"},
                {"2200 East Victory Drive","#36","Savannah","GA","31404"},
                {"16502 South Main Street","#5","Gardena","CA","90248"},
                {"116 Frost Park","","Hartford","VT","05001"},
                {"2504 Longest Avenue","","Louisville","KY","40204"},
                {"118 Pearl Street","","Manchester","CT","06040"},
                {"1383 Purdue Street","","San Leandro","CA","94579"},
                {"44 Downey Drive","","Manchester","CT","06040"},
                {"5938 Laguna Honda Street","","Redding","CA","96001"},
                {"1528 Stafford Avenue","","Hayward","CA","94541"},
                {"1215 Joseph Avenue","","Nashville","TN","37207"},
                {"634 Chautauqua Avenue","","Norman","OK","73069"},
                {"200 Hialeah Drive","","Glen Burnie","MD","21060"},
                {"11 Proctor Circle","","Peabody","MA","01960"},
                {"141 West Leslie Lane","","Panama City Beach","FL","32407"},
                {"31 Ashworth Street","","Manchester","CT","06040"},
                {"37 Kensington Street","","Manchester","CT","06040"},
                {"7326 North 62nd Avenue","#2","Glendale","AZ","85301"},
                {"200 Redwood Road","","Manchester","CT","06040"},
                {"6108 Iris Way","","Arvada","CO","80004"},
                {"2100 Sandy Creek Trail","","Edmond","OK","73013"},
                {"325 Baxter Lane","","Fayetteville","AR","72701"},
                {"2375 Main Street","B","Palmer","MA","01069"},
                {"7650 West 68th Avenue","#315","Arvada","CO","80004"},
                {"115 Falkirk Street","","Savannah","GA","31407"},
                {"597 East Miracle Drive","#4","Fayetteville","AR","72701"},
                {"68 Princeton Street","","Manchester","CT","06042"},
                {"1056 Cayer Drive","","Glen Burnie","MD","21061"},
                {"4455 West 61st Place","","Arvada","CO","80003"},
                {"172 Homestead Street","","Manchester","CT","06042"},
                {"6716 South Mariposa Lane","","Dublin","CA","94568"},
                {"2237 Northwest 18th Street","","Oklahoma City","OK","73107"},
                {"2504 Longest Avenue","","Louisville","KY","40204"},
                {"415 West 42nd Street","","Savannah","GA","31401"},
                {"309 Water Street","","North Little Rock","AR","72117"},
                {"7000 Hugh Drive","","Panama City","FL","32404"},
                {"2641 Heather Lane","","Redding","CA","96002"},
                {"2765 North Barcelona Avenue","","Fayetteville","AR","72703"},
                {"4408 Bonaparte Boulevard","","Midwest City","OK","73110"},
                {"5420 Allison Street","#202","Arvada","CO","80002"},
                {"1056 Cayer Drive","","Glen Burnie","MD","21061"},
                {"119 Redwood Road","","Manchester","CT","06040"},
                {"9399 Yucca Way","","Arvada","CO","80007"},
                {"4714 Narrow Lane Road","","Montgomery","AL","36116"},
                {"82 East Foster Street","#2","Melrose","MA","02176"},
                {"1243 Doris Avenue","","Pasadena","MD","21122"},
                {"7785 Montgomery Mews Court","","Severn","MD","21144"},
                {"21402 Caribbean Lane","","Panama City Beach","FL","32413"},
                {"1250 Pram Place","","Annapolis","MD","21403"},
                {"1713 Arrow Cove Lane","","Annapolis","MD","21401"},
                {"5108 Franklin Street","","Savannah","GA","31405"},
                {"3114 US Highway 98","","Mexico Beach","FL","32456"},
                {"2212 Daffin Drive","","Savannah","GA","31404"},
                {"3377 Sandstone Court","","Pleasanton","CA","94588"},
                {"320 Union Station Way","","Calera","AL","35040"},
                {"3538 Mendenhall Court","","Pleasanton","CA","94588"},
                {"649 Praderia Circle","","Fremont","CA","94539"},
                {"3300 Crystal Road","","Montgomery","AL","36110"},
                {"6754 Field Street","","Arvada","CO","80004"},
                {"43 Westminster Street","","Pittsfield","MA","01201"},
                {"7 Rose Dhu Lane","","Savannah","GA","31419"},
                {"8526 Preston Highway","","Louisville","KY","40219"},
                {"2654 Quiet Water Cove","","Annapolis","MD","21401"},
                {"95 Westbourne Way","","Pooler","GA","31322"},
                {"16205 Wind Crest Way","","Edmond","OK","73013"},
                {"4331 Foeburn Lane","","Louisville","KY","40207"},
                {"30 Tanner Street","","Manchester","CT","06042"},
                {"21 Glenoak Lane Northwest","B","Glen Burnie","MD","21061"},
                {"5300 Robinwood Road","","Louisville","KY","40218"},
                {"5209 North Dewey Avenue","","Oklahoma City","OK","73118"},
                {"514 East 38th Street","","Savannah","GA","31401"},
                {"2317 Fernwood Drive","","Nashville","TN","37216"},
                {"464 Springfield Street","","Wilbraham","MA","01095"},
                {"5 Meadow Lane","","Rutland","VT","05701"},
                {"34 Belair Drive","","Holbrook","MA","02343"},
                {"107 Laurel Green Court","","Savannah","GA","31419"},
                {"5 Centercrest Drive","","Tyngsborough","MA","01879"},
                {"2100 North Leverett Avenue","#112J","Fayetteville","AR","72703"},
                {"37122 Contra Costa Avenue","","Fremont","CA","94536"},
                {"22538 6th Street","","Hayward","CA","94541"},
                {"910 Arlington Terrace","","Fayetteville","AR","72701"},
                {"10312 Parlett Place","","Cupertino","CA","95014"},
                {"6605 North 93RD Avenue","#1016","Glendale","AZ","85305"},
                {"8 Kozera Avenue","","Hadley","MA","01035"},
                {"3537 West Chevaux Drive","","Fayetteville","AR","72704"},
                {"5110 East Shoshone Avenue","","Orange","CA","92867"},
                {"1583 Elberta Court","","Severn","MD","21144"},
                {"17 Gregory Street","","Middleton","MA","01949"},
                {"179 Blue Ridge Drive","","Manchester","CT","06040"},
                {"607 North 46th Avenue","","Fayetteville","AR","72704"},
                {"150 Meadowview Street","","Marshfield","MA","02050"},
                {"17722 North 79th Avenue","#1135","Glendale","AZ","85308"},
                {"200 Hialeah Drive","","Glen Burnie","MD","21060"},
                {"301 Willow Way","","Lynn Haven","FL","32444"}};
        this.addresses = addressGen;
    }

    // Getters
    public String[][] getReviews() { return reviews; }
    public String[] getNames() { return names; }
    public String[] getEmails() { return emails; }
    public String[][] getAddresses() { return addresses; }
}
