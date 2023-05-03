create database pcPokemon;
use pcPokemon;

create table Tipos(
	type_id int auto_increment,
    type_name enum('Fuego','Agua','Planta','Tierra','Roca','Acero','Lucha','Hielo','Dragon','Electrico',
    'Volador','Hada','Siniestro','Veneno','Fantasma','Psiquico','Bicho','Normal'),
    constraint PK_type primary key (type_id, type_name)
);

create table Usuario(
	user_login varchar(30) PRIMARY KEY,
    user_name varchar(30) NOT NULL,
    user_pass varchar(30) NOT NULL
);

insert into Usuario values("igor333", "Igor", "123",1);
insert into Usuario values("unai","Unai","12345",2);

create table PC(
	pc_id int PRIMARY KEY auto_increment,
	pc_boxes int default 8,
    user_login varchar(30) not null,
    constraint fk_pc_user foreign key (user_login) references Usuario(user_login)
    on update cascade
 );
 
 insert into PC values (1,8,"igor333");
 
create table Profesor(
	prof_login varchar(30) PRIMARY KEY,
    prof_name varchar(30) NOT NULL,
    prof_pass varchar(30) NOT NULL,
    prof_gen enum('Kanto','Jhoto','Hoenn','Sinnoh','Unova','Kalos')
);

insert into Profesor values ("oak","Profesor Oak","KantoKanto", "Kanto");
insert into Profesor values ("elm","Profesor Elm","JhotoJhoto","Jhoto");
insert into Profesor values ("abedul","Profesor Abedul","HoennHoenn","Hoenn");
insert into Profesor values ("serbal","Profesor Serbal","KalosKalos","Kalos");
insert into Profesor values ("encina","Profesora Encina","UnovaUnova","Unova");

create table Movimientos(
	mov_id int PRIMARY KEY auto_increment,
    move_name varchar(30),
    mov_type int NOT NULL,
    potency int default null,
    pp int default 5,
    accuracy int default 100,
	constraint fk_mov_type foreign key (mov_type) references Tipos(type_id)
    on update cascade
    );

insert into Movimientos values(2,'karate-chop',2,50,25,100),(3,'double-slap',1,15,10,85),(4,'comet-punch',1,18,15,85),(5,'mega-punch',1,80,20,85),(6,'pay-day',1,40,20,100),(7,'fire-punch',10,75,15,100),(8,'ice-punch',15,75,15,100),(9,'thunder-punch',13,75,15,100),(10,'scratch',1,40,35,100),(11,'vice-grip',1,55,30,100),(12,'guillotine',1,NULL,5,30),(13,'razor-wind',1,80,10,100),(14,'swords-dance',1,NULL,20,NULL),(15,'cut',1,50,30,95),(16,'gust',3,40,35,100),(17,'wing-attack',3,60,35,100),(18,'whirlwind',1,NULL,20,NULL),(19,'fly',3,90,15,95),(20,'bind',1,15,20,85),(21,'slam',1,80,20,75),(22,'vine-whip',12,45,25,100),(23,'stomp',1,65,20,100),(24,'double-kick',2,30,30,100),(25,'mega-kick',1,120,5,75),(26,'jump-kick',2,100,10,95),(27,'rolling-kick',2,60,15,85),(28,'sand-attack',5,NULL,15,100),(29,'headbutt',1,70,15,100),(30,'horn-attack',1,65,25,100),(31,'fury-attack',1,15,20,85),(32,'horn-drill',1,NULL,5,30),(33,'tackle',1,50,35,100),(34,'body-slam',1,85,15,100),(35,'wrap',1,15,20,90),(36,'take-down',1,90,20,85),(37,'thrash',1,120,10,100),(38,'double-edge',1,120,15,100),(39,'tail-whip',1,NULL,30,100),(40,'poison-sting',4,15,35,100),(41,'twineedle',7,25,20,100),(42,'pin-missile',7,25,20,95),(43,'leer',1,NULL,30,100),(44,'bite',17,60,25,100),(45,'growl',1,NULL,40,100),(46,'roar',1,NULL,20,NULL),(47,'sing',1,NULL,15,55),(48,'supersonic',1,NULL,20,55),(49,'sonic-boom',1,NULL,20,90),(50,'disable',1,NULL,20,100),(51,'acid',4,40,30,100),(52,'ember',10,40,25,100),(53,'flamethrower',10,90,15,100),(54,'mist',15,NULL,30,NULL),(55,'water-gun',11,40,25,100),(56,'hydro-pump',11,110,5,80),(57,'surf',11,90,15,100),(58,'ice-beam',15,90,10,100),(59,'blizzard',15,110,5,70),(60,'psybeam',14,65,20,100),(61,'bubble-beam',11,65,20,100),(62,'aurora-beam',15,65,20,100),(63,'hyper-beam',1,150,5,90),(64,'peck',3,35,35,100),(65,'drill-peck',3,80,20,100),(66,'submission',2,80,20,80),(67,'low-kick',2,NULL,20,100),(68,'counter',2,NULL,20,100),(69,'seismic-toss',2,NULL,20,100),(70,'strength',1,80,15,100),(71,'absorb',12,20,25,100),(72,'mega-drain',12,40,15,100),(73,'leech-seed',12,NULL,10,90),(74,'growth',1,NULL,20,NULL),(75,'razor-leaf',12,55,25,95),(76,'solar-beam',12,120,10,100),(77,'poison-powder',4,NULL,35,75),(78,'stun-spore',12,NULL,30,75),(79,'sleep-powder',12,NULL,15,75),(80,'petal-dance',12,120,10,100),(81,'string-shot',7,NULL,40,95),(82,'dragon-rage',16,NULL,10,100),(83,'fire-spin',10,35,15,85),(84,'thunder-shock',13,40,30,100),(85,'thunderbolt',13,90,15,100),(86,'thunder-wave',13,NULL,20,100),(87,'thunder',13,110,10,70),(88,'rock-throw',6,50,15,90),(89,'earthquake',5,100,10,100),(90,'fissure',5,NULL,5,30),(91,'dig',5,80,10,100),(92,'toxic',4,NULL,10,90),(93,'confusion',14,50,25,100),(94,'psychic',14,90,10,100),(95,'hypnosis',14,NULL,20,60),(96,'meditate',14,NULL,40,NULL),(97,'agility',14,NULL,30,NULL),(98,'quick-attack',1,40,30,100),(99,'rage',1,20,20,100),(100,'teleport',14,NULL,20,NULL),(101,'night-shade',8,NULL,15,100),(102,'mimic',1,NULL,10,NULL),(103,'screech',1,NULL,40,85),(104,'double-team',1,NULL,15,NULL),(105,'recover',1,NULL,10,NULL),(106,'harden',1,NULL,30,NULL),(107,'minimize',1,NULL,10,NULL),(108,'smokescreen',1,NULL,20,100),(109,'confuse-ray',8,NULL,10,100),(110,'withdraw',11,NULL,40,NULL),(111,'defense-curl',1,NULL,40,NULL),(112,'barrier',14,NULL,20,NULL),(113,'light-screen',14,NULL,30,NULL),(114,'haze',15,NULL,30,NULL),(115,'reflect',14,NULL,20,NULL),(116,'focus-energy',1,NULL,30,NULL),(117,'bide',1,NULL,10,NULL),(118,'metronome',1,NULL,10,NULL),(119,'mirror-move',3,NULL,20,NULL),(120,'self-destruct',1,200,5,100),(121,'egg-bomb',1,100,10,75),(122,'lick',8,30,30,100),(123,'smog',4,30,20,70),(124,'sludge',4,65,20,100),(125,'bone-club',5,65,20,85),(126,'fire-blast',10,110,5,85),(127,'waterfall',11,80,15,100),(128,'clamp',11,35,15,85),(129,'swift',1,60,20,NULL),(130,'skull-bash',1,130,10,100),(131,'spike-cannon',1,20,15,100),(132,'constrict',1,10,35,100),(133,'amnesia',14,NULL,20,NULL),(134,'kinesis',14,NULL,15,80),(135,'soft-boiled',1,NULL,10,NULL),(136,'high-jump-kick',2,130,10,90),(137,'glare',1,NULL,30,100),(138,'dream-eater',14,100,15,100),(139,'poison-gas',4,NULL,40,90),(140,'barrage',1,15,20,85),(141,'leech-life',7,20,15,100),(142,'lovely-kiss',1,NULL,10,75),(143,'sky-attack',3,140,5,90),(144,'transform',1,NULL,10,NULL),(145,'bubble',11,40,30,100),(146,'dizzy-punch',1,70,10,100),(147,'spore',12,NULL,15,100),(148,'flash',1,NULL,20,100),(149,'psywave',14,NULL,15,100),(150,'splash',1,NULL,40,NULL),(151,'acid-armor',4,NULL,20,NULL),(152,'crabhammer',11,100,10,90),(153,'explosion',1,250,5,100),(154,'fury-swipes',1,18,15,80),(155,'bonemerang',5,50,10,90),(156,'rest',14,NULL,10,NULL),(157,'rock-slide',6,75,10,90),(158,'hyper-fang',1,80,15,90),(159,'sharpen',1,NULL,30,NULL),(160,'conversion',1,NULL,30,NULL),(161,'tri-attack',1,80,10,100),(162,'super-fang',1,NULL,10,90),(163,'slash',1,70,20,100),(164,'substitute',1,NULL,10,NULL),(165,'struggle',1,50,NULL,NULL),(166,'sketch',1,NULL,1,NULL),(167,'triple-kick',2,10,10,90),(168,'thief',17,60,25,100),(169,'spider-web',7,NULL,10,NULL),(170,'mind-reader',1,NULL,5,NULL),(171,'nightmare',8,NULL,15,100),(172,'flame-wheel',10,60,25,100),(173,'snore',1,50,15,100),(174,'curse',8,NULL,10,NULL),(175,'flail',1,NULL,15,100),(176,'conversion-2',1,NULL,30,NULL),(177,'aeroblast',3,100,5,95),(178,'cotton-spore',12,NULL,40,100),(179,'reversal',2,NULL,15,100),(180,'spite',8,NULL,10,100),(181,'powder-snow',15,40,25,100),(182,'protect',1,NULL,10,NULL),(183,'mach-punch',2,40,30,100),(184,'scary-face',1,NULL,10,100),(185,'feint-attack',17,60,20,NULL),(186,'sweet-kiss',18,NULL,10,75),(187,'belly-drum',1,NULL,10,NULL),(188,'sludge-bomb',4,90,10,100),(189,'mud-slap',5,20,10,100),(190,'octazooka',11,65,10,85),(191,'spikes',5,NULL,20,NULL),(192,'zap-cannon',13,120,5,50),(193,'foresight',1,NULL,40,NULL),(194,'destiny-bond',8,NULL,5,NULL),(195,'perish-song',1,NULL,5,NULL),(196,'icy-wind',15,55,15,95),(197,'detect',2,NULL,5,NULL),(198,'bone-rush',5,25,10,90),(199,'lock-on',1,NULL,5,NULL),(200,'outrage',16,120,10,100),(201,'sandstorm',6,NULL,10,NULL),(202,'giga-drain',12,75,10,100),(203,'endure',1,NULL,10,NULL),(204,'charm',18,NULL,20,100),(205,'rollout',6,30,20,90),(206,'false-swipe',1,40,40,100),(207,'swagger',1,NULL,15,90),(208,'milk-drink',1,NULL,10,NULL),(209,'spark',13,65,20,100),(210,'fury-cutter',7,40,20,95),(211,'steel-wing',9,70,25,90),(212,'mean-look',1,NULL,5,NULL),(213,'attract',1,NULL,15,100),(214,'sleep-talk',1,NULL,10,NULL),(215,'heal-bell',1,NULL,5,NULL),(216,'return',1,NULL,20,100),(217,'present',1,NULL,15,90),(218,'frustration',1,NULL,20,100),(219,'safeguard',1,NULL,25,NULL),(220,'pain-split',1,NULL,20,NULL),(221,'sacred-fire',10,100,5,95),(222,'magnitude',5,NULL,30,100),(223,'dynamic-punch',2,100,5,50),(224,'megahorn',7,120,10,85),(225,'dragon-breath',16,60,20,100),(226,'baton-pass',1,NULL,40,NULL),(227,'encore',1,NULL,5,100),(228,'pursuit',17,40,20,100),(229,'rapid-spin',1,20,40,100),(230,'sweet-scent',1,NULL,20,100),(231,'iron-tail',9,100,15,75),(232,'metal-claw',9,50,35,95),(233,'vital-throw',2,70,10,NULL),(234,'morning-sun',1,NULL,5,NULL),(235,'synthesis',12,NULL,5,NULL),(236,'moonlight',18,NULL,5,NULL),(237,'hidden-power',1,60,15,100),(238,'cross-chop',2,100,5,80),(239,'twister',16,40,20,100),(240,'rain-dance',11,NULL,5,NULL),(241,'sunny-day',10,NULL,5,NULL),(242,'crunch',17,80,15,100),(243,'mirror-coat',14,NULL,20,100),(244,'psych-up',1,NULL,10,NULL),(245,'extreme-speed',1,80,5,100),(246,'ancient-power',6,60,5,100),(247,'shadow-ball',8,80,15,100),(248,'future-sight',14,120,10,100),(249,'rock-smash',2,40,15,100),(250,'whirlpool',11,35,15,85),(251,'beat-up',17,NULL,10,100),(252,'fake-out',1,40,10,100),(253,'uproar',1,90,10,100),(254,'stockpile',1,NULL,20,NULL),(255,'spit-up',1,NULL,10,100),(256,'swallow',1,NULL,10,NULL),(257,'heat-wave',10,95,10,90),(258,'hail',15,NULL,10,NULL),(259,'torment',17,NULL,15,100),(260,'flatter',17,NULL,15,100),(261,'will-o-wisp',10,NULL,15,85),(262,'memento',17,NULL,10,100),(263,'facade',1,70,20,100),(264,'focus-punch',2,150,20,100),(265,'smelling-salts',1,70,10,100),(266,'follow-me',1,NULL,20,NULL),(267,'nature-power',1,NULL,20,NULL),(268,'charge',13,NULL,20,NULL),(269,'taunt',17,NULL,20,100),(270,'helping-hand',1,NULL,20,NULL),(271,'trick',14,NULL,10,100),(272,'role-play',14,NULL,10,NULL),(273,'wish',1,NULL,10,NULL),(274,'assist',1,NULL,20,NULL),(275,'ingrain',12,NULL,20,NULL),(276,'superpower',2,120,5,100),(277,'magic-coat',14,NULL,15,NULL),(278,'recycle',1,NULL,10,NULL),(279,'revenge',2,60,10,100),(280,'brick-break',2,75,15,100),(281,'yawn',1,NULL,10,NULL),(282,'knock-off',17,65,20,100),(283,'endeavor',1,NULL,5,100),(284,'eruption',10,150,5,100),(285,'skill-swap',14,NULL,10,NULL),(286,'imprison',14,NULL,10,NULL),(287,'refresh',1,NULL,20,NULL),(288,'grudge',8,NULL,5,NULL),(289,'snatch',17,NULL,10,NULL),(290,'secret-power',1,70,20,100),(291,'dive',11,80,10,100),(292,'arm-thrust',2,15,20,100),(293,'camouflage',1,NULL,20,NULL),(294,'tail-glow',7,NULL,20,NULL),(295,'luster-purge',14,70,5,100),(296,'mist-ball',14,70,5,100),(297,'feather-dance',3,NULL,15,100),(298,'teeter-dance',1,NULL,20,100),(299,'blaze-kick',10,85,10,90),(300,'mud-sport',5,NULL,15,NULL),(301,'ice-ball',15,30,20,90),(302,'needle-arm',12,60,15,100),(303,'slack-off',1,NULL,10,NULL),(304,'hyper-voice',1,90,10,100),(305,'poison-fang',4,50,15,100),(306,'crush-claw',1,75,10,95),(307,'blast-burn',10,150,5,90),(308,'hydro-cannon',11,150,5,90),(309,'meteor-mash',9,90,10,90),(310,'astonish',8,30,15,100),(311,'weather-ball',1,50,10,100),(312,'aromatherapy',12,NULL,5,NULL),(313,'fake-tears',17,NULL,20,100),(314,'air-cutter',3,60,25,95),(315,'overheat',10,130,5,90),(316,'odor-sleuth',1,NULL,40,NULL),(317,'rock-tomb',6,60,15,95),(318,'silver-wind',7,60,5,100),(319,'metal-sound',9,NULL,40,85),(320,'grass-whistle',12,NULL,15,55),(321,'tickle',1,NULL,20,100),(322,'cosmic-power',14,NULL,20,NULL),(323,'water-spout',11,150,5,100),(324,'signal-beam',7,75,15,100),(325,'shadow-punch',8,60,20,NULL),(326,'extrasensory',14,80,20,100),(327,'sky-uppercut',2,85,15,90),(328,'sand-tomb',5,35,15,85),(329,'sheer-cold',15,NULL,5,30),(330,'muddy-water',11,90,10,85),(331,'bullet-seed',12,25,30,100),(332,'aerial-ace',3,60,20,NULL),(333,'icicle-spear',15,25,30,100),(334,'iron-defense',9,NULL,15,NULL),(335,'block',1,NULL,5,NULL),(336,'howl',1,NULL,40,NULL),(337,'dragon-claw',16,80,15,100),(338,'frenzy-plant',12,150,5,90),(339,'bulk-up',2,NULL,20,NULL),(340,'bounce',3,85,5,85),(341,'mud-shot',5,55,15,95),(342,'poison-tail',4,50,25,100),(343,'covet',1,60,25,100),(344,'volt-tackle',13,120,15,100),(345,'magical-leaf',12,60,20,NULL),(346,'water-sport',11,NULL,15,NULL),(347,'calm-mind',14,NULL,20,NULL),(348,'leaf-blade',12,90,15,100),(349,'dragon-dance',16,NULL,20,NULL),(350,'rock-blast',6,25,10,90),(351,'shock-wave',13,60,20,NULL),(352,'water-pulse',11,60,20,100),(353,'doom-desire',9,140,5,100),(354,'psycho-boost',14,140,5,90),(355,'roost',3,NULL,10,NULL),(356,'gravity',14,NULL,5,NULL),(357,'miracle-eye',14,NULL,40,NULL),(358,'wake-up-slap',2,70,10,100),(359,'hammer-arm',2,100,10,90),(360,'gyro-ball',9,NULL,5,100),(361,'healing-wish',14,NULL,10,NULL),(362,'brine',11,65,10,100),(363,'natural-gift',1,NULL,15,100),(364,'feint',1,30,10,100),(365,'pluck',3,60,20,100),(366,'tailwind',3,NULL,15,NULL),(367,'acupressure',1,NULL,30,NULL),(368,'metal-burst',9,NULL,10,100),(369,'u-turn',7,70,20,100),(370,'close-combat',2,120,5,100),(371,'payback',17,50,10,100),(372,'assurance',17,60,10,100),(373,'embargo',17,NULL,15,100),(374,'fling',17,NULL,10,100),(375,'psycho-shift',14,NULL,10,100),(376,'trump-card',1,NULL,5,NULL),(377,'heal-block',14,NULL,15,100),(378,'wring-out',1,NULL,5,100),(379,'power-trick',14,NULL,10,NULL),(380,'gastro-acid',4,NULL,10,100),(381,'lucky-chant',1,NULL,30,NULL),(382,'me-first',1,NULL,20,NULL),(383,'copycat',1,NULL,20,NULL),(384,'power-swap',14,NULL,10,NULL),(385,'guard-swap',14,NULL,10,NULL),(386,'punishment',17,NULL,5,100),(387,'last-resort',1,140,5,100),(388,'worry-seed',12,NULL,10,100),(389,'sucker-punch',17,80,5,100),(390,'toxic-spikes',4,NULL,20,NULL),(391,'heart-swap',14,NULL,10,NULL),(392,'aqua-ring',11,NULL,20,NULL),(393,'magnet-rise',13,NULL,10,NULL),(394,'flare-blitz',10,120,15,100),(395,'force-palm',2,60,10,100),(396,'aura-sphere',2,80,20,NULL),(397,'rock-polish',6,NULL,20,NULL),(398,'poison-jab',4,80,20,100),(399,'dark-pulse',17,80,15,100),(400,'night-slash',17,70,15,100),(401,'aqua-tail',11,90,10,90),(402,'seed-bomb',12,80,15,100),(403,'air-slash',3,75,15,95),(404,'x-scissor',7,80,15,100),(405,'bug-buzz',7,90,10,100),(406,'dragon-pulse',16,85,10,100),(407,'dragon-rush',16,100,10,75),(408,'power-gem',6,80,20,100),(409,'drain-punch',2,75,10,100),(410,'vacuum-wave',2,40,30,100),(411,'focus-blast',2,120,5,70),(412,'energy-ball',12,90,10,100),(413,'brave-bird',3,120,15,100),(414,'earth-power',5,90,10,100),(415,'switcheroo',17,NULL,10,100),(416,'giga-impact',1,150,5,90),(417,'nasty-plot',17,NULL,20,NULL),(418,'bullet-punch',9,40,30,100),(419,'avalanche',15,60,10,100),(420,'ice-shard',15,40,30,100),(421,'shadow-claw',8,70,15,100),(422,'thunder-fang',13,65,15,95),(423,'ice-fang',15,65,15,95),(424,'fire-fang',10,65,15,95),(425,'shadow-sneak',8,40,30,100),(426,'mud-bomb',5,65,10,85),(427,'psycho-cut',14,70,20,100),(428,'zen-headbutt',14,80,15,90),(429,'mirror-shot',9,65,10,85),(430,'flash-cannon',9,80,10,100),(431,'rock-climb',1,90,20,85),(432,'defog',3,NULL,15,NULL),(433,'trick-room',14,NULL,5,NULL),(434,'draco-meteor',16,130,5,90),(435,'discharge',13,80,15,100),(436,'lava-plume',10,80,15,100),(437,'leaf-storm',12,130,5,90),(438,'power-whip',12,120,10,85),(439,'rock-wrecker',6,150,5,90),(440,'cross-poison',4,70,20,100),(441,'gunk-shot',4,120,5,80),(442,'iron-head',9,80,15,100),(443,'magnet-bomb',9,60,20,NULL),(444,'stone-edge',6,100,5,80),(445,'captivate',1,NULL,20,100),(446,'stealth-rock',6,NULL,20,NULL),(447,'grass-knot',12,NULL,20,100),(448,'chatter',3,65,20,100),(449,'judgment',1,100,10,100),(450,'bug-bite',7,60,20,100),(451,'charge-beam',13,50,10,90),(452,'wood-hammer',12,120,15,100),(453,'aqua-jet',11,40,20,100),(454,'attack-order',7,90,15,100),(455,'defend-order',7,NULL,10,NULL),(456,'heal-order',7,NULL,10,NULL),(457,'head-smash',6,150,5,80),(458,'double-hit',1,35,10,90),(459,'roar-of-time',16,150,5,90),(460,'spacial-rend',16,100,5,95),(461,'lunar-dance',14,NULL,10,NULL),(462,'crush-grip',1,NULL,5,100),(463,'magma-storm',10,100,5,75),(464,'dark-void',17,NULL,10,80),(465,'seed-flare',12,120,5,85),(466,'ominous-wind',8,60,5,100),(467,'shadow-force',8,120,5,100),(468,'hone-claws',17,NULL,15,NULL),(469,'wide-guard',6,NULL,10,NULL),(470,'guard-split',14,NULL,10,NULL),(471,'power-split',14,NULL,10,NULL),(472,'wonder-room',14,NULL,10,NULL),(473,'psyshock',14,80,10,100),(474,'venoshock',4,65,10,100),(475,'autotomize',9,NULL,15,NULL),(476,'rage-powder',7,NULL,20,NULL),(477,'telekinesis',14,NULL,15,NULL),(478,'magic-room',14,NULL,10,NULL),(479,'smack-down',6,50,15,100),(480,'storm-throw',2,60,10,100),(481,'flame-burst',10,70,15,100),(482,'sludge-wave',4,95,10,100),(483,'quiver-dance',7,NULL,20,NULL),(484,'heavy-slam',9,NULL,10,100),(485,'synchronoise',14,120,10,100),(486,'electro-ball',13,NULL,10,100),(487,'soak',11,NULL,20,100),(488,'flame-charge',10,50,20,100),(489,'coil',4,NULL,20,NULL),(490,'low-sweep',2,65,20,100),(491,'acid-spray',4,40,20,100),(492,'foul-play',17,95,15,100),(493,'simple-beam',1,NULL,15,100),(494,'entrainment',1,NULL,15,100),(495,'after-you',1,NULL,15,NULL),(496,'round',1,60,15,100),(497,'echoed-voice',1,40,15,100),(498,'chip-away',1,70,20,100),(499,'clear-smog',4,50,15,NULL),(500,'stored-power',14,20,10,100),(501,'quick-guard',2,NULL,15,NULL),(502,'ally-switch',14,NULL,15,NULL),(503,'scald',11,80,15,100),(504,'shell-smash',1,NULL,15,NULL),(505,'heal-pulse',14,NULL,10,NULL),(506,'hex',8,65,10,100),(507,'sky-drop',3,60,10,100),(508,'shift-gear',9,NULL,10,NULL),(509,'circle-throw',2,60,10,90),(510,'incinerate',10,60,15,100),(511,'quash',17,NULL,15,100),(512,'acrobatics',3,55,15,100),(513,'reflect-type',1,NULL,15,NULL),(514,'retaliate',1,70,5,100),(515,'final-gambit',2,NULL,5,100),(516,'bestow',1,NULL,15,NULL),(517,'inferno',10,100,5,50),(518,'water-pledge',11,80,10,100),(519,'fire-pledge',10,80,10,100),(520,'grass-pledge',12,80,10,100),(521,'volt-switch',13,70,20,100),(522,'struggle-bug',7,50,20,100),(523,'bulldoze',5,60,20,100),(524,'frost-breath',15,60,10,90),(525,'dragon-tail',16,60,10,90),(526,'work-up',1,NULL,30,NULL),(527,'electroweb',13,55,15,95),(528,'wild-charge',13,90,15,100),(529,'drill-run',5,80,10,95),(530,'dual-chop',16,40,15,90),(531,'heart-stamp',14,60,25,100),(532,'horn-leech',12,75,10,100),(533,'sacred-sword',2,90,15,100),(534,'razor-shell',11,75,10,95),(535,'heat-crash',10,NULL,10,100),(536,'leaf-tornado',12,65,10,90),(537,'steamroller',7,65,20,100),(538,'cotton-guard',12,NULL,10,NULL),(539,'night-daze',17,85,10,95),(540,'psystrike',14,100,10,100),(541,'tail-slap',1,25,10,85),(542,'hurricane',3,110,10,70),(543,'head-charge',1,120,15,100),(544,'gear-grind',9,50,15,85),(545,'searing-shot',10,100,5,100),(546,'techno-blast',1,120,5,100),(547,'relic-song',1,75,10,100),(548,'secret-sword',2,85,10,100),(549,'glaciate',15,65,10,95),(550,'bolt-strike',13,130,5,85),(551,'blue-flare',10,130,5,85),(552,'fiery-dance',10,80,10,100),(553,'freeze-shock',15,140,5,90),(554,'ice-burn',15,140,5,90),(555,'snarl',17,55,15,95),(556,'icicle-crash',15,85,10,90),(557,'v-create',10,180,5,95),(558,'fusion-flare',10,100,5,100),(559,'fusion-bolt',13,100,5,100),(560,'flying-press',2,80,10,95),(561,'mat-block',2,NULL,10,NULL),(562,'belch',4,120,10,90),(563,'rototiller',5,NULL,10,NULL),(564,'sticky-web',7,NULL,20,NULL),(565,'fell-stinger',7,30,25,100),(566,'phantom-force',8,90,10,100),(567,'trick-or-treat',8,NULL,20,100),(568,'noble-roar',1,NULL,30,100),(569,'ion-deluge',13,NULL,25,NULL),(570,'parabolic-charge',13,50,20,100),(571,'forests-curse',12,NULL,20,100),(572,'petal-blizzard',12,90,15,100),(573,'freeze-dry',15,70,20,100),(574,'disarming-voice',18,40,15,NULL),(575,'parting-shot',17,NULL,20,100),(576,'topsy-turvy',17,NULL,20,NULL),(577,'draining-kiss',18,50,10,100),(578,'crafty-shield',18,NULL,10,NULL),(579,'flower-shield',18,NULL,10,NULL),(580,'grassy-terrain',12,NULL,10,NULL),(581,'misty-terrain',18,NULL,10,NULL),(582,'electrify',13,NULL,20,NULL),(583,'play-rough',18,90,10,90),(584,'fairy-wind',18,40,30,100),(585,'moonblast',18,95,15,100),(586,'boomburst',1,140,10,100),(587,'fairy-lock',18,NULL,10,NULL),(588,'kings-shield',9,NULL,10,NULL),(589,'play-nice',1,NULL,20,NULL),(590,'confide',1,NULL,20,NULL),(591,'diamond-storm',6,100,5,95),(592,'steam-eruption',11,110,5,95),(593,'hyperspace-hole',14,80,5,NULL),(594,'water-shuriken',11,15,20,100),(595,'mystical-fire',10,65,10,100),(596,'spiky-shield',12,NULL,10,NULL),(597,'aromatic-mist',18,NULL,20,NULL),(598,'eerie-impulse',13,NULL,15,100),(599,'venom-drench',4,NULL,20,100),(600,'powder',7,NULL,20,100),(601,'geomancy',18,NULL,10,NULL),(602,'magnetic-flux',13,NULL,20,NULL),(603,'happy-hour',1,NULL,30,NULL),(604,'electric-terrain',13,NULL,10,NULL),(605,'dazzling-gleam',18,80,10,100),(606,'celebrate',1,NULL,40,NULL),(607,'hold-hands',1,NULL,40,NULL),(608,'baby-doll-eyes',18,NULL,30,100),(609,'nuzzle',13,20,20,100),(610,'hold-back',1,40,40,100),(611,'infestation',7,20,20,100),(612,'power-up-punch',2,40,20,100),(613,'oblivion-wing',3,80,10,100),(614,'thousand-arrows',5,90,10,100),(615,'thousand-waves',5,90,10,100),(616,'lands-wrath',5,90,10,100),(617,'light-of-ruin',18,140,5,90),(618,'origin-pulse',11,110,10,85),(619,'precipice-blades',5,120,10,85),(620,'dragon-ascent',3,120,5,100),(621,'hyperspace-fury',17,100,5,NULL);
   
create table Caja(
	pc_box_id int PRIMARY KEY auto_increment,
    pc_id int,
	constraint fk_pc_id foreign key (pc_id) references PC(pc_id)
    on update cascade
);    

create table Pokemon (
	poke_id int PRIMARY KEY,
    poke_name varchar(30),
    poke_type1 int NOT NULL,
    poke_type2 int default null,
    descripcion varchar(350) not null,
    hp int default 10,
    atk int default 10,
    def int default 10,
	vel int default 10,
    spAtk int default 10,
    spDef int default 10,
    poke_gen enum('Kanto','Jhoto','Hoenn','Sinnoh','Unova','Kalos'),
    poke_mov1 int not null ,
		poke_mov2 int not null ,
			poke_mov3 int not null ,
				poke_mov4 int not null ,
                constraint fk_pk_type1 foreign key (poke_type1) references Tipos(type_id)
                on update cascade,
                constraint fk_pk_type2 foreign key (poke_type2) references Tipos(type_id)
                on update cascade,
                constraint fk_pk_mov1 foreign key (poke_mov1) references Movimientos(mov_id),
                constraint fk_pk_mov2 foreign key (poke_mov2) references Movimientos(mov_id),
				constraint fk_pk_mov3 foreign key (poke_mov3) references Movimientos(mov_id),
                constraint fk_pk_mov4 foreign key (poke_mov4) references Movimientos(mov_id)
);
insert into Tipos values (1,'Normal'),(2,'Lucha'),(3,'Volador'),(4,'Veneno'),(5,'Tierra'),(6,'Roca'),
(7,'Bicho'),(8,'Fantasma'),(9,'Acero'),(10,'Fuego'),(11,'Agua'),(12,'Planta'),(13,'Electrico'),
(14,'Psiquico'),(15,'Hielo'),(16,'Dragon'),(17,'Siniestro'),(18,'Hada');

Insert into Pokemon values(1,"Bulbasaur",12,4,"Descripcion generica",45,49,49,45,65,65,'Kanto',15,20,300,450);
Insert into Pokemon values(2,"Ivysaur",12,4,"Descripcion generica",60,62,63,60,80,80,'Kanto',15,20,300,450);
Insert into Pokemon values(3,"Venusaur",12,4,"Descripcion generica",80,82,83,80,100,100,'Kanto',15,20,300,450);
Insert into Pokemon values(4,"Charmander",10,null,"Descripcion generica",39,52,43,65,60,50,'Kanto',15,20,300,450);
Insert into Pokemon values(5,"Charmeleon",10,null,"Descripcion generica",58,64,58,80,80,65,'Kanto',15,20,300,450);
Insert into Pokemon values(6,"Charizard",10,3,"Descripcion generica",78,84,78,100,109,85,'Kanto',15,20,300,450);
Insert into Pokemon values(7,"Squirtle",12,null,"Descripcion generica",59,63,80,58,65,80,'Kanto',15,20,300,450);
Insert into Pokemon values(8,"Wartortle",12,null,"Descripcion generica",79,83,100,78,85,105,'Kanto',15,20,300,450);
Insert into Pokemon values(9,"Blastoise",12,null,"Descripcion generica",79,83,100,78,85,105,'Kanto',15,20,300,450);
Insert into Pokemon values(10,"Caterpie",7,null,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(11,"Metapod",7,null,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(12,"Butterfree",7,3,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(13,"Weedle",7,null,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(14,"Kakuna",7,null,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(15,"Beedrill",7,4,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(16,"Pidgey",1,3,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(17,"Pidgeotto",1,3,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(18,"PIdgey",1,3,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(19,"Rattata",1,null,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(20,"Raticate",1,null,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(21,"Spearow",1,3,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(22,"Fearow",1,3,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(23,"Ekans",4,null,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(24,"Arbok",4,null,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(25,"Pikachu",13,null,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);
Insert into Pokemon values(25,"Raichu",13,null,"Descripcion generica",50,50,50,50,50,50,'Kanto',15,20,300,450);


create table CajaPokemon(
	pc_id int,
    pc_box_id int,
    poke_id int,
    constraint PK_pc_box primary key (pc_id, pc_box_id),
    constraint fk_pc foreign key (pc_id) references PC(pc_id) 
    on update cascade,
    constraint fk_caja foreign key (pc_box_id) references Caja(pc_box_id)
    on update cascade,
    constraint fk_pokemon foreign key (poke_id) references pokemon(poke_id)
    on update cascade
);



create table equipo(
	id_equipo int PRIMARY KEY,
	user_login varchar(30),
    poke_id1 int not null,
    poke_id2 int,
    poke_id3 int,
    poke_id4 int,
    poke_id5 int,
    poke_id6 int,
    constraint FK_user foreign key (user_login) references Usuario(user_login)
    on update cascade,
    constraint FK_poke_team foreign key (poke_id1) references Pokemon(poke_id)
    on update cascade,
    constraint FK_poke_team2 foreign key (poke_id2) references Pokemon(poke_id)
    on update cascade,
    constraint FK_poke_team3 foreign key (poke_id3) references Pokemon(poke_id)
    on update cascade,
    constraint FK_poke_team4 foreign key (poke_id4) references Pokemon(poke_id)
    on update cascade,
    constraint FK_poke_team5 foreign key (poke_id5) references Pokemon(poke_id)
    on update cascade,
    constraint FK_poke_team6 foreign key (poke_id6) references Pokemon(poke_id)
    on update cascade
);

insert into Caja values  (1,1,0);
insert into CajaPokemon values(1,1,null);
insert into equipo values(1,"igor333",1,null,null,null,null,null);

/* AL REGISTRAR USUARIO -> CREAR TRIGGER PARA INTRODUCIR UN POKEMON EN LA BDD DE EQUIPO ,
HACER UNA CAJA PARA EL USUARIO, PC, Y CAJAPOKEMON 
drop table equipo;
drop table cajapokemon;
drop table Pokemon;

use pcpokemon;
SELECT * from Movimientos;

SELECT poke_id, poke_name,T1.type_name Tipo1 ,T2.type_name Tipo2,descripcion,hp,atk,def,vel,spAtk,spDef,poke_gen from pokemon join tipos T1 on poke_type1=T1.type_id join tipos T2 on poke_type2=T2.type_id where poke_id=1 ;
*/