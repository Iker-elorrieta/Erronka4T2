create database pcPokemon;
use pcPokemon;

/*Creación de tabla Tipo */
create table Tipo(
	type_id int,
    type_name enum('Fuego','Agua','Planta','Tierra','Roca','Acero','Lucha','Hielo','Dragon','Electrico',
    'Volador','Hada','Siniestro','Veneno','Fantasma','Psiquico','Bicho','Normal') unique,
    constraint PK_type primary key (type_id, type_name)
);

insert into Tipo values (1,'Normal'),(2,'Lucha'),(3,'Volador'),(4,'Veneno'),(5,'Tierra'),(6,'Roca'),
(7,'Bicho'),(8,'Fantasma'),(9,'Acero'),(10,'Fuego'),(11,'Agua'),(12,'Planta'),(13,'Electrico'),
(14,'Psiquico'),(15,'Hielo'),(16,'Dragon'),(17,'Siniestro'),(18,'Hada');

/*Creación de tabla Region */
create table region(
	reg_id int not null unique,
    reg_name enum('Kanto','Johto','Hoenn','Sinnoh','Unova','Kalos') unique,
    constraint PK_region primary key (reg_id, reg_name)
);

insert into Region values (1,'Kanto'),(2,'Johto'),(3,'Hoenn'),(4,'Sinnoh'),(5,'Unova'),(6,'Kalos');

/*Creación de tabla Usuario*/
create table Usuario(
	user_login varchar(30) PRIMARY KEY,
    user_name varchar(30) NOT NULL,
    user_pass varchar(30) NOT NULL,
    user_team int default 1,
    baneado boolean default false
);

/*Creación de tabla PC*/
create table PC(
	pc_id int PRIMARY KEY auto_increment,
    user_login varchar(30) not null,
    constraint fk_pc_user foreign key (user_login) references Usuario(user_login)
    on update cascade
 );
 
 /*Creación de tabla Profesor*/
create table Profesor(
	prof_login varchar(30) PRIMARY KEY,
    prof_name varchar(30) NOT NULL,
    prof_pass varchar(30) NOT NULL,
    prof_gen int not null unique,
    constraint fk_pro_region foreign key (prof_gen) references Region(reg_id)
);

insert into Profesor values ("oak","Profesor Oak","KantoKanto", 1);
insert into Profesor values ("elm","Profesor Elm","JhotoJhoto",2);
insert into Profesor values ("abedul","Profesor Abedul","HoennHoenn",3);
insert into Profesor values ("serbal","Profesor Serbal","KalosKalos",6);
insert into Profesor values ("encina","Profesora Encina","UnovaUnova",5);

/*Creación de tabla Movimiento*/
create table Movimiento(
	mov_id int PRIMARY KEY auto_increment,
    move_name varchar(30) unique,
    mov_type int,
    potency int default null,
    pp int default 5,
    accuracy int default 100,
	constraint fk_mov_type foreign key (mov_type) references Tipo(type_id)
    on update cascade

    );

insert into Movimiento values(2,'karate-chop',2,50,25,100),(3,'double-slap',1,15,10,85),(4,'comet-punch',1,18,15,85),(5,'mega-punch',1,80,20,85),(6,'pay-day',1,40,20,100),(7,'fire-punch',10,75,15,100),(8,'ice-punch',15,75,15,100),(9,'thunder-punch',13,75,15,100),(10,'scratch',1,40,35,100),(11,'vice-grip',1,55,30,100),(12,'guillotine',1,NULL,5,30),(13,'razor-wind',1,80,10,100),(14,'swords-dance',1,NULL,20,NULL),(15,'cut',1,50,30,95),(16,'gust',3,40,35,100),(17,'wing-attack',3,60,35,100),(18,'whirlwind',1,NULL,20,NULL),(19,'fly',3,90,15,95),(20,'bind',1,15,20,85),(21,'slam',1,80,20,75),(22,'vine-whip',12,45,25,100),(23,'stomp',1,65,20,100),(24,'double-kick',2,30,30,100),(25,'mega-kick',1,120,5,75),(26,'jump-kick',2,100,10,95),(27,'rolling-kick',2,60,15,85),(28,'sand-attack',5,NULL,15,100),(29,'headbutt',1,70,15,100),(30,'horn-attack',1,65,25,100),(31,'fury-attack',1,15,20,85),(32,'horn-drill',1,NULL,5,30),(33,'tackle',1,50,35,100),(34,'body-slam',1,85,15,100),(35,'wrap',1,15,20,90),(36,'take-down',1,90,20,85),(37,'thrash',1,120,10,100),(38,'double-edge',1,120,15,100),(39,'tail-whip',1,NULL,30,100),(40,'poison-sting',4,15,35,100),(41,'twineedle',7,25,20,100),(42,'pin-missile',7,25,20,95),(43,'leer',1,NULL,30,100),(44,'bite',17,60,25,100),(45,'growl',1,NULL,40,100),(46,'roar',1,NULL,20,NULL),(47,'sing',1,NULL,15,55),(48,'supersonic',1,NULL,20,55),(49,'sonic-boom',1,NULL,20,90),(50,'disable',1,NULL,20,100),(51,'acid',4,40,30,100),(52,'ember',10,40,25,100),(53,'flamethrower',10,90,15,100),(54,'mist',15,NULL,30,NULL),(55,'water-gun',11,40,25,100),(56,'hydro-pump',11,110,5,80),(57,'surf',11,90,15,100),(58,'ice-beam',15,90,10,100),(59,'blizzard',15,110,5,70),(60,'psybeam',14,65,20,100),(61,'bubble-beam',11,65,20,100),(62,'aurora-beam',15,65,20,100),(63,'hyper-beam',1,150,5,90),(64,'peck',3,35,35,100),(65,'drill-peck',3,80,20,100),(66,'submission',2,80,20,80),(67,'low-kick',2,NULL,20,100),(68,'counter',2,NULL,20,100),(69,'seismic-toss',2,NULL,20,100),(70,'strength',1,80,15,100),(71,'absorb',12,20,25,100),(72,'mega-drain',12,40,15,100),(73,'leech-seed',12,NULL,10,90),(74,'growth',1,NULL,20,NULL),(75,'razor-leaf',12,55,25,95),(76,'solar-beam',12,120,10,100),(77,'poison-powder',4,NULL,35,75),(78,'stun-spore',12,NULL,30,75),(79,'sleep-powder',12,NULL,15,75),(80,'petal-dance',12,120,10,100),(81,'string-shot',7,NULL,40,95),(82,'dragon-rage',16,NULL,10,100),(83,'fire-spin',10,35,15,85),(84,'thunder-shock',13,40,30,100),(85,'thunderbolt',13,90,15,100),(86,'thunder-wave',13,NULL,20,100),(87,'thunder',13,110,10,70),(88,'rock-throw',6,50,15,90),(89,'earthquake',5,100,10,100),(90,'fissure',5,NULL,5,30),(91,'dig',5,80,10,100),(92,'toxic',4,NULL,10,90),(93,'confusion',14,50,25,100),(94,'psychic',14,90,10,100),(95,'hypnosis',14,NULL,20,60),(96,'meditate',14,NULL,40,NULL),(97,'agility',14,NULL,30,NULL),(98,'quick-attack',1,40,30,100),(99,'rage',1,20,20,100),(100,'teleport',14,NULL,20,NULL),(101,'night-shade',8,NULL,15,100),(102,'mimic',1,NULL,10,NULL),(103,'screech',1,NULL,40,85),(104,'double-team',1,NULL,15,NULL),(105,'recover',1,NULL,10,NULL),(106,'harden',1,NULL,30,NULL),(107,'minimize',1,NULL,10,NULL),(108,'smokescreen',1,NULL,20,100),(109,'confuse-ray',8,NULL,10,100),(110,'withdraw',11,NULL,40,NULL),(111,'defense-curl',1,NULL,40,NULL),(112,'barrier',14,NULL,20,NULL),(113,'light-screen',14,NULL,30,NULL),(114,'haze',15,NULL,30,NULL),(115,'reflect',14,NULL,20,NULL),(116,'focus-energy',1,NULL,30,NULL),(117,'bide',1,NULL,10,NULL),(118,'metronome',1,NULL,10,NULL),(119,'mirror-move',3,NULL,20,NULL),(120,'self-destruct',1,200,5,100),(121,'egg-bomb',1,100,10,75),(122,'lick',8,30,30,100),(123,'smog',4,30,20,70),(124,'sludge',4,65,20,100),(125,'bone-club',5,65,20,85),(126,'fire-blast',10,110,5,85),(127,'waterfall',11,80,15,100),(128,'clamp',11,35,15,85),(129,'swift',1,60,20,NULL),(130,'skull-bash',1,130,10,100),(131,'spike-cannon',1,20,15,100),(132,'constrict',1,10,35,100),(133,'amnesia',14,NULL,20,NULL),(134,'kinesis',14,NULL,15,80),(135,'soft-boiled',1,NULL,10,NULL),(136,'high-jump-kick',2,130,10,90),(137,'glare',1,NULL,30,100),(138,'dream-eater',14,100,15,100),(139,'poison-gas',4,NULL,40,90),(140,'barrage',1,15,20,85),(141,'leech-life',7,20,15,100),(142,'lovely-kiss',1,NULL,10,75),(143,'sky-attack',3,140,5,90),(144,'transform',1,NULL,10,NULL),(145,'bubble',11,40,30,100),(146,'dizzy-punch',1,70,10,100),(147,'spore',12,NULL,15,100),(148,'flash',1,NULL,20,100),(149,'psywave',14,NULL,15,100),(150,'splash',1,NULL,40,NULL),(151,'acid-armor',4,NULL,20,NULL),(152,'crabhammer',11,100,10,90),(153,'explosion',1,250,5,100),(154,'fury-swipes',1,18,15,80),(155,'bonemerang',5,50,10,90),(156,'rest',14,NULL,10,NULL),(157,'rock-slide',6,75,10,90),(158,'hyper-fang',1,80,15,90),(159,'sharpen',1,NULL,30,NULL),(160,'conversion',1,NULL,30,NULL),(161,'tri-attack',1,80,10,100),(162,'super-fang',1,NULL,10,90),(163,'slash',1,70,20,100),(164,'substitute',1,NULL,10,NULL),(165,'struggle',1,50,NULL,NULL),(166,'sketch',1,NULL,1,NULL),(167,'triple-kick',2,10,10,90),(168,'thief',17,60,25,100),(169,'spider-web',7,NULL,10,NULL),(170,'mind-reader',1,NULL,5,NULL),(171,'nightmare',8,NULL,15,100),(172,'flame-wheel',10,60,25,100),(173,'snore',1,50,15,100),(174,'curse',8,NULL,10,NULL),(175,'flail',1,NULL,15,100),(176,'conversion-2',1,NULL,30,NULL),(177,'aeroblast',3,100,5,95),(178,'cotton-spore',12,NULL,40,100),(179,'reversal',2,NULL,15,100),(180,'spite',8,NULL,10,100),(181,'powder-snow',15,40,25,100),(182,'protect',1,NULL,10,NULL),(183,'mach-punch',2,40,30,100),(184,'scary-face',1,NULL,10,100),(185,'feint-attack',17,60,20,NULL),(186,'sweet-kiss',18,NULL,10,75),(187,'belly-drum',1,NULL,10,NULL),(188,'sludge-bomb',4,90,10,100),(189,'mud-slap',5,20,10,100),(190,'octazooka',11,65,10,85),(191,'spikes',5,NULL,20,NULL),(192,'zap-cannon',13,120,5,50),(193,'foresight',1,NULL,40,NULL),(194,'destiny-bond',8,NULL,5,NULL),(195,'perish-song',1,NULL,5,NULL),(196,'icy-wind',15,55,15,95),(197,'detect',2,NULL,5,NULL),(198,'bone-rush',5,25,10,90),(199,'lock-on',1,NULL,5,NULL),(200,'outrage',16,120,10,100),(201,'sandstorm',6,NULL,10,NULL),(202,'giga-drain',12,75,10,100),(203,'endure',1,NULL,10,NULL),(204,'charm',18,NULL,20,100),(205,'rollout',6,30,20,90),(206,'false-swipe',1,40,40,100),(207,'swagger',1,NULL,15,90),(208,'milk-drink',1,NULL,10,NULL),(209,'spark',13,65,20,100),(210,'fury-cutter',7,40,20,95),(211,'steel-wing',9,70,25,90),(212,'mean-look',1,NULL,5,NULL),(213,'attract',1,NULL,15,100),(214,'sleep-talk',1,NULL,10,NULL),(215,'heal-bell',1,NULL,5,NULL),(216,'return',1,NULL,20,100),(217,'present',1,NULL,15,90),(218,'frustration',1,NULL,20,100),(219,'safeguard',1,NULL,25,NULL),(220,'pain-split',1,NULL,20,NULL),(221,'sacred-fire',10,100,5,95),(222,'magnitude',5,NULL,30,100),(223,'dynamic-punch',2,100,5,50),(224,'megahorn',7,120,10,85),(225,'dragon-breath',16,60,20,100),(226,'baton-pass',1,NULL,40,NULL),(227,'encore',1,NULL,5,100),(228,'pursuit',17,40,20,100),(229,'rapid-spin',1,20,40,100),(230,'sweet-scent',1,NULL,20,100),(231,'iron-tail',9,100,15,75),(232,'metal-claw',9,50,35,95),(233,'vital-throw',2,70,10,NULL),(234,'morning-sun',1,NULL,5,NULL),(235,'synthesis',12,NULL,5,NULL),(236,'moonlight',18,NULL,5,NULL),(237,'hidden-power',1,60,15,100),(238,'cross-chop',2,100,5,80),(239,'twister',16,40,20,100),(240,'rain-dance',11,NULL,5,NULL),(241,'sunny-day',10,NULL,5,NULL),(242,'crunch',17,80,15,100),(243,'mirror-coat',14,NULL,20,100),(244,'psych-up',1,NULL,10,NULL),(245,'extreme-speed',1,80,5,100),(246,'ancient-power',6,60,5,100),(247,'shadow-ball',8,80,15,100),(248,'future-sight',14,120,10,100),(249,'rock-smash',2,40,15,100),(250,'whirlpool',11,35,15,85),(251,'beat-up',17,NULL,10,100),(252,'fake-out',1,40,10,100),(253,'uproar',1,90,10,100),(254,'stockpile',1,NULL,20,NULL),(255,'spit-up',1,NULL,10,100),(256,'swallow',1,NULL,10,NULL),(257,'heat-wave',10,95,10,90),(258,'hail',15,NULL,10,NULL),(259,'torment',17,NULL,15,100),(260,'flatter',17,NULL,15,100),(261,'will-o-wisp',10,NULL,15,85),(262,'memento',17,NULL,10,100),(263,'facade',1,70,20,100),(264,'focus-punch',2,150,20,100),(265,'smelling-salts',1,70,10,100),(266,'follow-me',1,NULL,20,NULL),(267,'nature-power',1,NULL,20,NULL),(268,'charge',13,NULL,20,NULL),(269,'taunt',17,NULL,20,100),(270,'helping-hand',1,NULL,20,NULL),(271,'trick',14,NULL,10,100),(272,'role-play',14,NULL,10,NULL),(273,'wish',1,NULL,10,NULL),(274,'assist',1,NULL,20,NULL),(275,'ingrain',12,NULL,20,NULL),(276,'superpower',2,120,5,100),(277,'magic-coat',14,NULL,15,NULL),(278,'recycle',1,NULL,10,NULL),(279,'revenge',2,60,10,100),(280,'brick-break',2,75,15,100),(281,'yawn',1,NULL,10,NULL),(282,'knock-off',17,65,20,100),(283,'endeavor',1,NULL,5,100),(284,'eruption',10,150,5,100),(285,'skill-swap',14,NULL,10,NULL),(286,'imprison',14,NULL,10,NULL),(287,'refresh',1,NULL,20,NULL),(288,'grudge',8,NULL,5,NULL),(289,'snatch',17,NULL,10,NULL),(290,'secret-power',1,70,20,100),(291,'dive',11,80,10,100),(292,'arm-thrust',2,15,20,100),(293,'camouflage',1,NULL,20,NULL),(294,'tail-glow',7,NULL,20,NULL),(295,'luster-purge',14,70,5,100),(296,'mist-ball',14,70,5,100),(297,'feather-dance',3,NULL,15,100),(298,'teeter-dance',1,NULL,20,100),(299,'blaze-kick',10,85,10,90),(300,'mud-sport',5,NULL,15,NULL),(301,'ice-ball',15,30,20,90),(302,'needle-arm',12,60,15,100),(303,'slack-off',1,NULL,10,NULL),(304,'hyper-voice',1,90,10,100),(305,'poison-fang',4,50,15,100),(306,'crush-claw',1,75,10,95),(307,'blast-burn',10,150,5,90),(308,'hydro-cannon',11,150,5,90),(309,'meteor-mash',9,90,10,90),(310,'astonish',8,30,15,100),(311,'weather-ball',1,50,10,100),(312,'aromatherapy',12,NULL,5,NULL),(313,'fake-tears',17,NULL,20,100),(314,'air-cutter',3,60,25,95),(315,'overheat',10,130,5,90),(316,'odor-sleuth',1,NULL,40,NULL),(317,'rock-tomb',6,60,15,95),(318,'silver-wind',7,60,5,100),(319,'metal-sound',9,NULL,40,85),(320,'grass-whistle',12,NULL,15,55),(321,'tickle',1,NULL,20,100),(322,'cosmic-power',14,NULL,20,NULL),(323,'water-spout',11,150,5,100),(324,'signal-beam',7,75,15,100),(325,'shadow-punch',8,60,20,NULL),(326,'extrasensory',14,80,20,100),(327,'sky-uppercut',2,85,15,90),(328,'sand-tomb',5,35,15,85),(329,'sheer-cold',15,NULL,5,30),(330,'muddy-water',11,90,10,85),(331,'bullet-seed',12,25,30,100),(332,'aerial-ace',3,60,20,NULL),(333,'icicle-spear',15,25,30,100),(334,'iron-defense',9,NULL,15,NULL),(335,'block',1,NULL,5,NULL),(336,'howl',1,NULL,40,NULL),(337,'dragon-claw',16,80,15,100),(338,'frenzy-plant',12,150,5,90),(339,'bulk-up',2,NULL,20,NULL),(340,'bounce',3,85,5,85),(341,'mud-shot',5,55,15,95),(342,'poison-tail',4,50,25,100),(343,'covet',1,60,25,100),(344,'volt-tackle',13,120,15,100),(345,'magical-leaf',12,60,20,NULL),(346,'water-sport',11,NULL,15,NULL),(347,'calm-mind',14,NULL,20,NULL),(348,'leaf-blade',12,90,15,100),(349,'dragon-dance',16,NULL,20,NULL),(350,'rock-blast',6,25,10,90),(351,'shock-wave',13,60,20,NULL),(352,'water-pulse',11,60,20,100),(353,'doom-desire',9,140,5,100),(354,'psycho-boost',14,140,5,90),(355,'roost',3,NULL,10,NULL),(356,'gravity',14,NULL,5,NULL),(357,'miracle-eye',14,NULL,40,NULL),(358,'wake-up-slap',2,70,10,100),(359,'hammer-arm',2,100,10,90),(360,'gyro-ball',9,NULL,5,100),(361,'healing-wish',14,NULL,10,NULL),(362,'brine',11,65,10,100),(363,'natural-gift',1,NULL,15,100),(364,'feint',1,30,10,100),(365,'pluck',3,60,20,100),(366,'tailwind',3,NULL,15,NULL),(367,'acupressure',1,NULL,30,NULL),(368,'metal-burst',9,NULL,10,100),(369,'u-turn',7,70,20,100),(370,'close-combat',2,120,5,100),(371,'payback',17,50,10,100),(372,'assurance',17,60,10,100),(373,'embargo',17,NULL,15,100),(374,'fling',17,NULL,10,100),(375,'psycho-shift',14,NULL,10,100),(376,'trump-card',1,NULL,5,NULL),(377,'heal-block',14,NULL,15,100),(378,'wring-out',1,NULL,5,100),(379,'power-trick',14,NULL,10,NULL),(380,'gastro-acid',4,NULL,10,100),(381,'lucky-chant',1,NULL,30,NULL),(382,'me-first',1,NULL,20,NULL),(383,'copycat',1,NULL,20,NULL),(384,'power-swap',14,NULL,10,NULL),(385,'guard-swap',14,NULL,10,NULL),(386,'punishment',17,NULL,5,100),(387,'last-resort',1,140,5,100),(388,'worry-seed',12,NULL,10,100),(389,'sucker-punch',17,80,5,100),(390,'toxic-spikes',4,NULL,20,NULL),(391,'heart-swap',14,NULL,10,NULL),(392,'aqua-ring',11,NULL,20,NULL),(393,'magnet-rise',13,NULL,10,NULL),(394,'flare-blitz',10,120,15,100),(395,'force-palm',2,60,10,100),(396,'aura-sphere',2,80,20,NULL),(397,'rock-polish',6,NULL,20,NULL),(398,'poison-jab',4,80,20,100),(399,'dark-pulse',17,80,15,100),(400,'night-slash',17,70,15,100),(401,'aqua-tail',11,90,10,90),(402,'seed-bomb',12,80,15,100),(403,'air-slash',3,75,15,95),(404,'x-scissor',7,80,15,100),(405,'bug-buzz',7,90,10,100),(406,'dragon-pulse',16,85,10,100),(407,'dragon-rush',16,100,10,75),(408,'power-gem',6,80,20,100),(409,'drain-punch',2,75,10,100),(410,'vacuum-wave',2,40,30,100),(411,'focus-blast',2,120,5,70),(412,'energy-ball',12,90,10,100),(413,'brave-bird',3,120,15,100),(414,'earth-power',5,90,10,100),(415,'switcheroo',17,NULL,10,100),(416,'giga-impact',1,150,5,90),(417,'nasty-plot',17,NULL,20,NULL),(418,'bullet-punch',9,40,30,100),(419,'avalanche',15,60,10,100),(420,'ice-shard',15,40,30,100),(421,'shadow-claw',8,70,15,100),(422,'thunder-fang',13,65,15,95),(423,'ice-fang',15,65,15,95),(424,'fire-fang',10,65,15,95),(425,'shadow-sneak',8,40,30,100),(426,'mud-bomb',5,65,10,85),(427,'psycho-cut',14,70,20,100),(428,'zen-headbutt',14,80,15,90),(429,'mirror-shot',9,65,10,85),(430,'flash-cannon',9,80,10,100),(431,'rock-climb',1,90,20,85),(432,'defog',3,NULL,15,NULL),(433,'trick-room',14,NULL,5,NULL),(434,'draco-meteor',16,130,5,90),(435,'discharge',13,80,15,100),(436,'lava-plume',10,80,15,100),(437,'leaf-storm',12,130,5,90),(438,'power-whip',12,120,10,85),(439,'rock-wrecker',6,150,5,90),(440,'cross-poison',4,70,20,100),(441,'gunk-shot',4,120,5,80),(442,'iron-head',9,80,15,100),(443,'magnet-bomb',9,60,20,NULL),(444,'stone-edge',6,100,5,80),(445,'captivate',1,NULL,20,100),(446,'stealth-rock',6,NULL,20,NULL),(447,'grass-knot',12,NULL,20,100),(448,'chatter',3,65,20,100),(449,'judgment',1,100,10,100),(450,'bug-bite',7,60,20,100),(451,'charge-beam',13,50,10,90),(452,'wood-hammer',12,120,15,100),(453,'aqua-jet',11,40,20,100),(454,'attack-order',7,90,15,100),(455,'defend-order',7,NULL,10,NULL),(456,'heal-order',7,NULL,10,NULL),(457,'head-smash',6,150,5,80),(458,'double-hit',1,35,10,90),(459,'roar-of-time',16,150,5,90),(460,'spacial-rend',16,100,5,95),(461,'lunar-dance',14,NULL,10,NULL),(462,'crush-grip',1,NULL,5,100),(463,'magma-storm',10,100,5,75),(464,'dark-void',17,NULL,10,80),(465,'seed-flare',12,120,5,85),(466,'ominous-wind',8,60,5,100),(467,'shadow-force',8,120,5,100),(468,'hone-claws',17,NULL,15,NULL),(469,'wide-guard',6,NULL,10,NULL),(470,'guard-split',14,NULL,10,NULL),(471,'power-split',14,NULL,10,NULL),(472,'wonder-room',14,NULL,10,NULL),(473,'psyshock',14,80,10,100),(474,'venoshock',4,65,10,100),(475,'autotomize',9,NULL,15,NULL),(476,'rage-powder',7,NULL,20,NULL),(477,'telekinesis',14,NULL,15,NULL),(478,'magic-room',14,NULL,10,NULL),(479,'smack-down',6,50,15,100),(480,'storm-throw',2,60,10,100),(481,'flame-burst',10,70,15,100),(482,'sludge-wave',4,95,10,100),(483,'quiver-dance',7,NULL,20,NULL),(484,'heavy-slam',9,NULL,10,100),(485,'synchronoise',14,120,10,100),(486,'electro-ball',13,NULL,10,100),(487,'soak',11,NULL,20,100),(488,'flame-charge',10,50,20,100),(489,'coil',4,NULL,20,NULL),(490,'low-sweep',2,65,20,100),(491,'acid-spray',4,40,20,100),(492,'foul-play',17,95,15,100),(493,'simple-beam',1,NULL,15,100),(494,'entrainment',1,NULL,15,100),(495,'after-you',1,NULL,15,NULL),(496,'round',1,60,15,100),(497,'echoed-voice',1,40,15,100),(498,'chip-away',1,70,20,100),(499,'clear-smog',4,50,15,NULL),(500,'stored-power',14,20,10,100),(501,'quick-guard',2,NULL,15,NULL),(502,'ally-switch',14,NULL,15,NULL),(503,'scald',11,80,15,100),(504,'shell-smash',1,NULL,15,NULL),(505,'heal-pulse',14,NULL,10,NULL),(506,'hex',8,65,10,100),(507,'sky-drop',3,60,10,100),(508,'shift-gear',9,NULL,10,NULL),(509,'circle-throw',2,60,10,90),(510,'incinerate',10,60,15,100),(511,'quash',17,NULL,15,100),(512,'acrobatics',3,55,15,100),(513,'reflect-type',1,NULL,15,NULL),(514,'retaliate',1,70,5,100),(515,'final-gambit',2,NULL,5,100),(516,'bestow',1,NULL,15,NULL),(517,'inferno',10,100,5,50),(518,'water-pledge',11,80,10,100),(519,'fire-pledge',10,80,10,100),(520,'grass-pledge',12,80,10,100),(521,'volt-switch',13,70,20,100),(522,'struggle-bug',7,50,20,100),(523,'bulldoze',5,60,20,100),(524,'frost-breath',15,60,10,90),(525,'dragon-tail',16,60,10,90),(526,'work-up',1,NULL,30,NULL),(527,'electroweb',13,55,15,95),(528,'wild-charge',13,90,15,100),(529,'drill-run',5,80,10,95),(530,'dual-chop',16,40,15,90),(531,'heart-stamp',14,60,25,100),(532,'horn-leech',12,75,10,100),(533,'sacred-sword',2,90,15,100),(534,'razor-shell',11,75,10,95),(535,'heat-crash',10,NULL,10,100),(536,'leaf-tornado',12,65,10,90),(537,'steamroller',7,65,20,100),(538,'cotton-guard',12,NULL,10,NULL),(539,'night-daze',17,85,10,95),(540,'psystrike',14,100,10,100),(541,'tail-slap',1,25,10,85),(542,'hurricane',3,110,10,70),(543,'head-charge',1,120,15,100),(544,'gear-grind',9,50,15,85),(545,'searing-shot',10,100,5,100),(546,'techno-blast',1,120,5,100),(547,'relic-song',1,75,10,100),(548,'secret-sword',2,85,10,100),(549,'glaciate',15,65,10,95),(550,'bolt-strike',13,130,5,85),(551,'blue-flare',10,130,5,85),(552,'fiery-dance',10,80,10,100),(553,'freeze-shock',15,140,5,90),(554,'ice-burn',15,140,5,90),(555,'snarl',17,55,15,95),(556,'icicle-crash',15,85,10,90),(557,'v-create',10,180,5,95),(558,'fusion-flare',10,100,5,100),(559,'fusion-bolt',13,100,5,100),(560,'flying-press',2,80,10,95),(561,'mat-block',2,NULL,10,NULL),(562,'belch',4,120,10,90),(563,'rototiller',5,NULL,10,NULL),(564,'sticky-web',7,NULL,20,NULL),(565,'fell-stinger',7,30,25,100),(566,'phantom-force',8,90,10,100),(567,'trick-or-treat',8,NULL,20,100),(568,'noble-roar',1,NULL,30,100),(569,'ion-deluge',13,NULL,25,NULL),(570,'parabolic-charge',13,50,20,100),(571,'forests-curse',12,NULL,20,100),(572,'petal-blizzard',12,90,15,100),(573,'freeze-dry',15,70,20,100),(574,'disarming-voice',18,40,15,NULL),(575,'parting-shot',17,NULL,20,100),(576,'topsy-turvy',17,NULL,20,NULL),(577,'draining-kiss',18,50,10,100),(578,'crafty-shield',18,NULL,10,NULL),(579,'flower-shield',18,NULL,10,NULL),(580,'grassy-terrain',12,NULL,10,NULL),(581,'misty-terrain',18,NULL,10,NULL),(582,'electrify',13,NULL,20,NULL),(583,'play-rough',18,90,10,90),(584,'fairy-wind',18,40,30,100),(585,'moonblast',18,95,15,100),(586,'boomburst',1,140,10,100),(587,'fairy-lock',18,NULL,10,NULL),(588,'kings-shield',9,NULL,10,NULL),(589,'play-nice',1,NULL,20,NULL),(590,'confide',1,NULL,20,NULL),(591,'diamond-storm',6,100,5,95),(592,'steam-eruption',11,110,5,95),(593,'hyperspace-hole',14,80,5,NULL),(594,'water-shuriken',11,15,20,100),(595,'mystical-fire',10,65,10,100),(596,'spiky-shield',12,NULL,10,NULL),(597,'aromatic-mist',18,NULL,20,NULL),(598,'eerie-impulse',13,NULL,15,100),(599,'venom-drench',4,NULL,20,100),(600,'powder',7,NULL,20,100),(601,'geomancy',18,NULL,10,NULL),(602,'magnetic-flux',13,NULL,20,NULL),(603,'happy-hour',1,NULL,30,NULL),(604,'electric-terrain',13,NULL,10,NULL),(605,'dazzling-gleam',18,80,10,100),(606,'celebrate',1,NULL,40,NULL),(607,'hold-hands',1,NULL,40,NULL),(608,'baby-doll-eyes',18,NULL,30,100),(609,'nuzzle',13,20,20,100),(610,'hold-back',1,40,40,100),(611,'infestation',7,20,20,100),(612,'power-up-punch',2,40,20,100),(613,'oblivion-wing',3,80,10,100),(614,'thousand-arrows',5,90,10,100),(615,'thousand-waves',5,90,10,100),(616,'lands-wrath',5,90,10,100),(617,'light-of-ruin',18,140,5,90),(618,'origin-pulse',11,110,10,85),(619,'precipice-blades',5,120,10,85),(620,'dragon-ascent',3,120,5,100),(621,'hyperspace-fury',17,100,5,NULL);

/*Creación de tabla Caja*/
create table Caja(
	pc_box_id int PRIMARY KEY auto_increment,
    pc_id int,
    box_pokemon int default 0 check (box_pokemon <= 30),
	constraint fk_pc_id foreign key (pc_id) references PC(pc_id)
    on update cascade
);    

/*Creación de tabla Pokemon*/
create table Pokemon (
	poke_id int PRIMARY KEY,
    poke_name varchar(30),
    poke_type1 int NOT NULL,
    poke_type2 int default null,
    descripcion varchar(350) not null,
    hp int check (hp> 0 and hp <= 255),
    atk int check (atk> 0 and atk <= 255),
    def int check (def> 0 and def <= 255),
	vel int check (vel> 0 and vel <= 255),
    spAtk int check (spAtk> 0 and spAtk <= 255),
    spDef int check (spDef> 0 and spDef <= 255),
    poke_gen int not null,
    poke_mov1 int not null ,
		poke_mov2 int not null ,
			poke_mov3 int not null ,
				poke_mov4 int not null ,
                constraint fk_pk_type1 foreign key (poke_type1) references Tipo(type_id)
                on update cascade,
                constraint fk_pk_type2 foreign key (poke_type2) references Tipo(type_id)
                on update cascade,
                constraint fk_pk_mov1 foreign key (poke_mov1) references Movimiento(mov_id),
                constraint fk_pk_mov2 foreign key (poke_mov2) references Movimiento(mov_id),
				constraint fk_pk_mov3 foreign key (poke_mov3) references Movimiento(mov_id),
                constraint fk_pk_mov4 foreign key (poke_mov4) references Movimiento(mov_id),
                constraint fk_region_poke foreign key (poke_gen) references Region(reg_id)
);


/*Creación de tabla CajaPokemon*/
create table CajaPokemon(
	pc_id int,
    pc_box_id int,
    poke_id int,
    constraint PK_pc_box primary key (pc_id, pc_box_id),
    constraint fk_pc foreign key (pc_id) references PC(pc_id) 
    on update cascade,
    constraint fk_caja foreign key (pc_box_id) references Caja(pc_box_id)
    on update cascade,
    constraint fk_pokemon foreign key (poke_id) references Pokemon(poke_id)
    on update cascade
);

/*Creación de tabla Equipo*/
create table Equipo(
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

insert into Pokemon values (721,"Volcanion",10,11,"",80,110,120,70,130,90,6,33,33,33,33);
insert into Pokemon values (720,"Hoopa",14,8,"",80,110,60,70,150,130,6,33,33,33,33);
insert into Pokemon values (719,"Diancie",6,18,"",50,100,150,50,100,150,6,33,33,33,33);
insert into Pokemon values (718,"Zygarde",16,5,"",108,100,121,95,81,95,6,33,33,33,33);
insert into Pokemon values (717,"Yveltal",17,3,"",126,131,95,99,131,98,6,33,33,33,33);
insert into Pokemon values (716,"Xerneas",18,null,"",126,131,95,99,131,98,6,33,33,33,33);
insert into Pokemon values (715,"Noivern",3,16,"",85,70,80,123,97,80,6,33,33,33,33);
insert into Pokemon values (714,"Noibat",3,16,"",40,30,35,55,45,40,6,33,33,33,33);
insert into Pokemon values (713,"Avalugg",15,null,"",95,117,184,28,44,46,6,33,33,33,33);
insert into Pokemon values (712,"Bergmite",15,null,"",55,69,85,28,32,35,6,33,33,33,33);
insert into Pokemon values (711,"Gourgeist",8,12,"",55,85,122,99,58,75,6,33,33,33,33);
insert into Pokemon values (710,"Pumpkaboo",8,12,"",44,66,70,56,44,55,6,33,33,33,33);
insert into Pokemon values (709,"Trevenant",8,12,"",85,110,76,56,65,82,6,33,33,33,33);
insert into Pokemon values (708,"Phantump",8,12,"",43,70,48,38,50,60,6,33,33,33,33);
insert into Pokemon values (707,"Klefki",9,18,"",57,80,91,75,80,87,6,33,33,33,33);
insert into Pokemon values (706,"Goodra",16,null,"",90,100,70,80,110,150,6,33,33,33,33);
insert into Pokemon values (705,"Sliggoo",16,null,"",68,75,53,60,83,113,6,33,33,33,33);
insert into Pokemon values (704,"Goomy",16,null,"",45,50,35,40,55,75,6,33,33,33,33);
insert into Pokemon values (703,"Carbink",6,18,"",50,50,150,50,50,150,6,33,33,33,33);
insert into Pokemon values (702,"Dedenne",13,18,"",67,58,57,101,81,67,6,33,33,33,33);
insert into Pokemon values (701,"Hawlucha",2,3,"",78,92,75,118,74,63,6,33,33,33,33);
insert into Pokemon values (700,"Sylveon",18,null,"",95,65,60,60,110,130,6,33,33,33,33);
insert into Pokemon values (699,"Aurorus",6,15,"",123,77,72,58,99,92,6,33,33,33,33);
insert into Pokemon values (698,"Amaura",6,15,"",77,59,50,46,67,63,6,33,33,33,33);
insert into Pokemon values (697,"Tyrantrum",6,16,"",82,121,119,71,69,59,6,33,33,33,33);
insert into Pokemon values (696,"Tyrunt",6,16,"",58,89,77,48,45,45,6,33,33,33,33);
insert into Pokemon values (695,"Heliolisk",13,1,"",62,55,52,109,109,94,6,33,33,33,33);
insert into Pokemon values (694,"Helioptile",13,1,"",44,38,33,70,61,43,6,33,33,33,33);
insert into Pokemon values (693,"Clawitzer",11,null,"",71,73,88,59,120,89,6,33,33,33,33);
insert into Pokemon values (692,"Clauncher",11,null,"",50,53,62,44,58,63,6,33,33,33,33);
insert into Pokemon values (691,"Dragalge",4,16,"",65,75,90,44,97,123,6,33,33,33,33);
insert into Pokemon values (690,"Skrelp",4,11,"",50,60,60,30,60,60,6,33,33,33,33);
insert into Pokemon values (689,"Barbaracle",6,11,"",72,105,115,68,54,86,6,33,33,33,33);
insert into Pokemon values (688,"Binacle",6,11,"",42,52,67,50,39,56,6,33,33,33,33);
insert into Pokemon values (687,"Malamar",17,14,"",86,92,88,73,68,75,6,33,33,33,33);
insert into Pokemon values (686,"Inkay",17,14,"",53,54,53,45,37,46,6,33,33,33,33);
insert into Pokemon values (685,"Slurpuff",18,null,"",82,80,86,72,85,75,6,33,33,33,33);
insert into Pokemon values (684,"Swirlix",18,null,"",62,48,66,49,59,57,6,33,33,33,33);
insert into Pokemon values (683,"Aromatisse",18,null,"",101,72,72,29,99,89,6,33,33,33,33);
insert into Pokemon values (682,"Spritzee",18,null,"",78,52,60,23,63,65,6,33,33,33,33);
insert into Pokemon values (681,"Aegislash",9,8,"",60,50,150,60,50,150,6,33,33,33,33);
insert into Pokemon values (680,"Doublade",9,8,"",59,110,150,35,45,49,6,33,33,33,33);
insert into Pokemon values (679,"Honedge",9,8,"",45,80,100,28,35,37,6,33,33,33,33);
insert into Pokemon values (678,"Meowstic",14,null,"",74,48,76,104,83,81,6,33,33,33,33);
insert into Pokemon values (677,"Espurr",14,null,"",62,48,54,68,63,60,6,33,33,33,33);
insert into Pokemon values (676,"Furfrou",1,null,"",75,80,60,102,65,90,6,33,33,33,33);
insert into Pokemon values (675,"Pangoro",2,17,"",95,124,78,58,69,71,6,33,33,33,33);
insert into Pokemon values (674,"Pancham",2,null,"",67,82,62,43,46,48,6,33,33,33,33);
insert into Pokemon values (673,"Gogoat",12,null,"",123,100,62,68,97,81,6,33,33,33,33);
insert into Pokemon values (672,"Skiddo",12,null,"",66,65,48,52,62,57,6,33,33,33,33);
insert into Pokemon values (671,"Florges",18,null,"",78,65,68,75,112,154,6,33,33,33,33);
insert into Pokemon values (670,"Floette",18,null,"",54,45,47,52,75,98,6,33,33,33,33);
insert into Pokemon values (669,"Flabébé",18,null,"",44,38,39,42,61,79,6,33,33,33,33);
insert into Pokemon values (668,"Pyroar",10,1,"",86,68,72,106,109,66,6,33,33,33,33);
insert into Pokemon values (667,"Litleo",10,1,"",62,50,58,72,73,54,6,33,33,33,33);
insert into Pokemon values (666,"Vivillon",7,3,"",80,52,50,89,90,50,6,33,33,33,33);
insert into Pokemon values (665,"Spewga",7,null,"",45,22,60,29,27,30,6,33,33,33,33);
insert into Pokemon values (664,"Scatterbug",7,null,"",38,35,40,35,27,25,6,33,33,33,33);
insert into Pokemon values (663,"Talonflame",10,3,"",78,81,71,126,74,69,6,33,33,33,33);
insert into Pokemon values (662,"Fletchinder",10,3,"",62,73,55,84,56,52,6,33,33,33,33);
insert into Pokemon values (661,"Fletchling",1,3,"",45,50,43,62,40,38,6,33,33,33,33);
insert into Pokemon values (660,"Diggersby",1,5,"",85,56,77,78,50,77,6,33,33,33,33);
insert into Pokemon values (659,"Bunnelby",1,null,"",38,36,38,57,32,36,6,33,33,33,33);
insert into Pokemon values (658,"Greninja",11,17,"",72,95,67,122,103,71,6,33,33,33,33);
insert into Pokemon values (657,"Frogadier",11,null,"",54,63,52,97,83,56,6,33,33,33,33);
insert into Pokemon values (656,"Froakie",11,null,"",41,56,40,71,62,44,6,33,33,33,33);
insert into Pokemon values (655,"Delphox",10,14,"",75,69,72,104,114,100,6,33,33,33,33);
insert into Pokemon values (654,"Braixen",10,null,"",59,59,58,73,90,70,6,33,33,33,33);
insert into Pokemon values (653,"Fennekin",10,null,"",40,45,40,60,62,60,6,33,33,33,33);
insert into Pokemon values (652,"Chesnaught",12,2,"",88,107,122,64,74,75,6,33,33,33,33);
insert into Pokemon values (651,"Quilladin",12,null,"",61,78,95,57,56,58,6,33,33,33,33);
insert into Pokemon values (650,"Chespin",12,null,"",56,61,65,38,48,45,6,33,33,33,33);



insert into Pokemon values (649,"Genesect",7,9,"",71,120,95,99,120,95,5,33,33,33,33);
insert into Pokemon values (648,"Meloetta",1,14,"",100,77,77,90,128,128,5,33,33,33,33);
insert into Pokemon values (647,"Keldeo",11,2,"",91,72,90,108,129,90,5,33,33,33,33);
insert into Pokemon values (646,"Kyurem",16,15,"",125,130,90,95,130,90,5,33,33,33,33);
insert into Pokemon values (645,"Landorus",5,3,"",89,125,90,101,115,80,5,33,33,33,33);
insert into Pokemon values (644,"Zekrom",16,13,"",100,150,120,90,120,100,5,33,33,33,33);
insert into Pokemon values (643,"Reshiram",16,10,"",100,120,100,90,150,120,5,33,33,33,33);
insert into Pokemon values (642,"Thundurus",13,3,"",79,115,70,111,125,80,5,33,33,33,33);
insert into Pokemon values (641,"Tornadus",3,null,"",79,115,70,111,125,80,5,33,33,33,33);
insert into Pokemon values (640,"Virizion",12,2,"",91,90,72,108,90,129,5,33,33,33,33);
insert into Pokemon values (639,"Terrakion",6,2,"",91,129,90,108,72,90,5,33,33,33,33);
insert into Pokemon values (638,"Cobalion",9,2,"",91,90,129,108,90,72,5,33,33,33,33);
insert into Pokemon values (637,"Volcarona",7,10,"",85,60,65,100,135,105,5,33,33,33,33);
insert into Pokemon values (636,"Larvesta",7,10,"",55,85,55,60,50,55,5,33,33,33,33);
insert into Pokemon values (635,"Hydreigon",17,16,"",92,105,90,98,125,90,5,33,33,33,33);
insert into Pokemon values (634,"Zweilous",17,16,"",72,85,70,58,65,70,5,33,33,33,33);
insert into Pokemon values (633,"Deino",17,16,"",52,65,50,38,45,50,5,33,33,33,33);
insert into Pokemon values (632,"Durant",7,9,"",58,109,112,109,48,48,5,33,33,33,33);
insert into Pokemon values (631,"Heatmor",10,null,"",85,97,66,65,105,66,5,33,33,33,33);
insert into Pokemon values (630,"Mandibuzz",17,3,"",110,65,105,80,55,95,5,33,33,33,33);
insert into Pokemon values (629,"Vullaby",17,3,"",70,55,75,60,45,65,5,33,33,33,33);
insert into Pokemon values (628,"Braviary",1,3,"",100,123,75,80,57,75,5,33,33,33,33);
insert into Pokemon values (627,"Rufflet",1,3,"",70,83,50,60,37,50,5,33,33,33,33);
insert into Pokemon values (626,"Bouffalant",1,null,"",95,110,95,55,40,95,5,33,33,33,33);
insert into Pokemon values (625,"Bisharp",9,17,"",65,125,100,70,60,70,5,33,33,33,33);
insert into Pokemon values (624,"Pawniard",9,17,"",45,85,70,60,40,40,5,33,33,33,33);
insert into Pokemon values (623,"Golurk",5,8,"",89,124,80,55,55,80,5,33,33,33,33);
insert into Pokemon values (622,"Golett",5,8,"",59,74,50,35,35,50,5,33,33,33,33);
insert into Pokemon values (621,"Druddigon",16,null,"",77,120,90,48,60,90,5,33,33,33,33);
insert into Pokemon values (620,"Mienshao",2,null,"",65,125,60,105,95,60,5,33,33,33,33);
insert into Pokemon values (619,"Mienfoo",2,null,"",45,85,50,65,55,50,5,33,33,33,33);
insert into Pokemon values (618,"Stunfisk",13,5,"",109,66,84,32,81,99,5,33,33,33,33);
insert into Pokemon values (617,"Accelgor",7,null,"",80,70,40,145,100,60,5,33,33,33,33);
insert into Pokemon values (616,"Shelmet",7,null,"",50,40,85,25,40,65,5,33,33,33,33);
insert into Pokemon values (615,"Cryogonal",15,null,"",80,50,50,105,95,135,5,33,33,33,33);
insert into Pokemon values (614,"Beartic",15,null,"",95,130,80,50,70,80,5,33,33,33,33);
insert into Pokemon values (613,"Cubchoo",15,null,"",55,70,40,40,60,40,5,33,33,33,33);
insert into Pokemon values (612,"Haxorus",16,null,"",76,147,90,97,60,70,5,33,33,33,33);
insert into Pokemon values (611,"Fraxure",16,null,"",66,117,70,67,40,50,5,33,33,33,33);
insert into Pokemon values (610,"Axew",16,null,"",46,87,60,57,30,40,5,33,33,33,33);
insert into Pokemon values (609,"Chandelure",8,10,"",60,55,90,80,145,90,5,33,33,33,33);
insert into Pokemon values (608,"Lampent",8,10,"",60,40,60,55,95,60,5,33,33,33,33);
insert into Pokemon values (607,"Litwick",8,10,"",50,30,55,20,65,55,5,33,33,33,33);
insert into Pokemon values (606,"Beheeyem",14,null,"",75,75,75,40,125,95,5,33,33,33,33);
insert into Pokemon values (605,"Elgyem",14,null,"",55,55,55,30,85,55,5,33,33,33,33);
insert into Pokemon values (604,"Eelektross",13,null,"",85,115,80,50,105,80,5,33,33,33,33);
insert into Pokemon values (603,"Eelektrik",13,null,"",65,85,70,40,75,70,5,33,33,33,33);
insert into Pokemon values (602,"Tynamo",13,null,"",35,55,40,60,45,40,5,33,33,33,33);
insert into Pokemon values (601,"Klinklang",9,null,"",60,100,115,90,70,85,5,33,33,33,33);
insert into Pokemon values (600,"Klang",9,null,"",60,80,95,50,70,85,5,33,33,33,33);
insert into Pokemon values (599,"Klink",9,null,"",40,55,70,30,45,60,5,33,33,33,33);
insert into Pokemon values (598,"Ferrothorn",12,9,"",74,94,131,20,54,116,5,33,33,33,33);
insert into Pokemon values (597,"Ferroseed",12,9,"",44,50,91,10,24,86,5,33,33,33,33);
insert into Pokemon values (596,"Galvantula",7,13,"",70,77,60,108,97,60,5,33,33,33,33);
insert into Pokemon values (595,"Joltik",7,13,"",50,47,50,65,57,50,5,33,33,33,33);
insert into Pokemon values (594,"Alomomola",11,null,"",165,75,80,65,40,45,5,33,33,33,33);
insert into Pokemon values (593,"Jellicent",11,8,"",100,60,70,60,85,105,5,33,33,33,33);
insert into Pokemon values (592,"Frillish",11,8,"",55,40,50,40,65,85,5,33,33,33,33);
insert into Pokemon values (591,"Amoonguss",12,4,"",114,85,70,30,85,80,5,33,33,33,33);
insert into Pokemon values (590,"Foongus",12,4,"",69,55,45,15,55,55,5,33,33,33,33);
insert into Pokemon values (589,"Escavalier",7,9,"",70,135,105,20,60,105,5,33,33,33,33);
insert into Pokemon values (588,"Karrablast",7,null,"",50,75,45,60,40,45,5,33,33,33,33);
insert into Pokemon values (587,"Emolga",13,3,"",55,75,60,103,75,60,5,33,33,33,33);
insert into Pokemon values (586,"Sawsbuck",1,12,"",80,100,70,95,60,70,5,33,33,33,33);
insert into Pokemon values (585,"Deerling",1,12,"",60,60,50,75,40,50,5,33,33,33,33);
insert into Pokemon values (584,"Vanilluxe",15,null,"",71,95,85,79,110,95,5,33,33,33,33);
insert into Pokemon values (583,"Vanillish",15,null,"",51,65,65,59,80,75,5,33,33,33,33);
insert into Pokemon values (582,"Vanillite",15,null,"",36,50,50,44,65,60,5,33,33,33,33);
insert into Pokemon values (581,"Swanna",11,3,"",75,87,63,98,87,63,5,33,33,33,33);
insert into Pokemon values (580,"Ducklett",11,3,"",62,44,50,55,44,50,5,33,33,33,33);
insert into Pokemon values (579,"Reuniclus",14,null,"",110,65,75,30,125,85,5,33,33,33,33);
insert into Pokemon values (578,"Duosion",14,null,"",65,40,50,30,125,60,5,33,33,33,33);
insert into Pokemon values (577,"Solosis",14,null,"",45,30,40,20,105,50,5,33,33,33,33);
insert into Pokemon values (576,"Gothitelle",14,null,"",70,55,95,65,95,110,5,33,33,33,33);
insert into Pokemon values (575,"Gothorita",14,null,"",60,45,70,55,75,85,5,33,33,33,33);
insert into Pokemon values (574,"Gothita",14,null,"",45,30,50,45,55,65,5,33,33,33,33);
insert into Pokemon values (573,"Cinccino",1,null,"",75,95,60,115,65,60,5,33,33,33,33);
insert into Pokemon values (572,"Minccino",1,null,"",55,50,40,75,40,40,5,33,33,33,33);
insert into Pokemon values (571,"Zoroark",17,null,"",60,105,60,105,120,60,5,33,33,33,33);
insert into Pokemon values (570,"Zorua",17,null,"",40,65,40,65,80,40,5,33,33,33,33);
insert into Pokemon values (569,"Garbodor",4,null,"",80,95,82,75,60,82,5,33,33,33,33);
insert into Pokemon values (568,"Trubbish",4,null,"",50,50,62,65,40,62,5,33,33,33,33);
insert into Pokemon values (567,"Archeops",6,3,"",75,140,65,110,112,65,5,33,33,33,33);
insert into Pokemon values (566,"Archen",6,3,"",55,112,45,70,74,45,5,33,33,33,33);
insert into Pokemon values (565,"Carracosta",11,6,"",74,108,133,32,83,65,5,33,33,33,33);
insert into Pokemon values (564,"Tirtouga",11,6,"",54,78,103,22,53,45,5,33,33,33,33);
insert into Pokemon values (563,"Cofagrigus",8,null,"",58,50,145,30,95,105,5,33,33,33,33);
insert into Pokemon values (562,"Yamask",8,null,"",38,30,85,30,55,65,5,33,33,33,33);
insert into Pokemon values (561,"Sigilyph",14,3,"",72,58,80,97,103,80,5,33,33,33,33);
insert into Pokemon values (560,"Scrafty",17,2,"",65,90,115,58,45,115,5,33,33,33,33);
insert into Pokemon values (559,"Scraggy",17,2,"",50,75,70,48,35,70,5,33,33,33,33);
insert into Pokemon values (558,"Crustle",7,6,"",70,105,125,45,65,75,5,33,33,33,33);
insert into Pokemon values (557,"Dwebble",7,6,"",50,65,85,55,35,35,5,33,33,33,33);
insert into Pokemon values (556,"Maractus",12,null,"",75,86,67,60,106,67,5,33,33,33,33);
insert into Pokemon values (555,"Darmanitan",10,null,"",105,140,55,95,30,55,5,33,33,33,33);
insert into Pokemon values (554,"Darumaka",10,null,"",70,90,45,50,15,45,5,33,33,33,33);
insert into Pokemon values (553,"Krookodile",5,17,"",95,117,80,92,65,70,5,33,33,33,33);
insert into Pokemon values (552,"Krokorok",5,17,"",60,82,45,74,45,45,5,33,33,33,33);
insert into Pokemon values (551,"Sandile",5,17,"",50,72,35,65,35,35,5,33,33,33,33);
insert into Pokemon values (550,"Basculin",11,null,"",70,92,65,98,80,55,5,33,33,33,33);
insert into Pokemon values (549,"Lilligant",12,null,"",70,60,75,90,110,75,5,33,33,33,33);
insert into Pokemon values (548,"Petilil",12,null,"",45,35,50,30,70,50,5,33,33,33,33);
insert into Pokemon values (547,"Whimsicott",12,18,"",60,67,85,116,77,75,5,33,33,33,33);
insert into Pokemon values (546,"Cottonee",12,18,"",40,27,60,66,37,50,5,33,33,33,33);
insert into Pokemon values (545,"Scolipede",7,4,"",60,100,89,112,55,69,5,33,33,33,33);
insert into Pokemon values (544,"Whirlipede",7,4,"",40,55,99,47,40,79,5,33,33,33,33);
insert into Pokemon values (543,"Venipede",7,4,"",30,45,59,57,30,39,5,33,33,33,33);
insert into Pokemon values (542,"Leavanny",7,12,"",75,103,80,92,70,80,5,33,33,33,33);
insert into Pokemon values (541,"Swadloon",7,12,"",55,63,90,42,50,80,5,33,33,33,33);
insert into Pokemon values (540,"Sewaddle",7,12,"",45,53,70,42,40,60,5,33,33,33,33);
insert into Pokemon values (539,"Sawk",2,null,"",75,125,75,85,30,75,5,33,33,33,33);
insert into Pokemon values (538,"Throh",2,null,"",120,100,85,45,30,85,5,33,33,33,33);
insert into Pokemon values (537,"Seismitoad",11,5,"",105,95,75,74,85,75,5,33,33,33,33);
insert into Pokemon values (536,"Palpitoad",11,5,"",75,65,55,69,65,55,5,33,33,33,33);
insert into Pokemon values (535,"Tympole",11,null,"",50,50,40,64,50,40,5,33,33,33,33);
insert into Pokemon values (534,"Conkeldurr",2,null,"",105,140,95,45,55,65,5,33,33,33,33);
insert into Pokemon values (533,"Gurdurr",2,null,"",85,105,85,40,40,50,5,33,33,33,33);
insert into Pokemon values (532,"Timburr",2,null,"",75,80,55,35,25,35,5,33,33,33,33);
insert into Pokemon values (531,"Audino",1,null,"",103,60,86,50,60,86,5,33,33,33,33);
insert into Pokemon values (530,"Excadrill",5,9,"",110,135,60,88,50,65,5,33,33,33,33);
insert into Pokemon values (529,"Drilbur",5,null,"",60,85,40,68,30,45,5,33,33,33,33);
insert into Pokemon values (528,"Swoobat",14,null,"",67,57,55,114,77,55,5,33,33,33,33);
insert into Pokemon values (527,"Woobat",14,3,"",65,45,43,72,55,43,5,33,33,33,33);
insert into Pokemon values (526,"Gigalith",6,null,"",85,135,130,25,60,80,5,33,33,33,33);
insert into Pokemon values (525,"Boldore",6,null,"",70,105,105,20,50,40,5,33,33,33,33);
insert into Pokemon values (524,"Roggenrola",6,null,"",55,75,85,15,25,25,5,33,33,33,33);
insert into Pokemon values (523,"Zebstrika",13,null,"",75,100,63,116,80,63,5,33,33,33,33);
insert into Pokemon values (522,"Blitzle",13,null,"",45,60,32,76,50,32,5,33,33,33,33);
insert into Pokemon values (521,"Unfezant",1,3,"",80,115,80,93,65,55,5,33,33,33,33);
insert into Pokemon values (520,"Tranquill",1,3,"",62,77,62,65,50,42,5,33,33,33,33);
insert into Pokemon values (519,"Pidove",1,3,"",50,55,50,43,36,30,5,33,33,33,33);
insert into Pokemon values (518,"Musharna",14,null,"",116,55,85,29,107,95,5,33,33,33,33);
insert into Pokemon values (517,"Munna",14,null,"",76,25,45,24,67,55,5,33,33,33,33);
insert into Pokemon values (516,"Simipour",11,null,"",75,98,63,101,98,63,5,33,33,33,33);
insert into Pokemon values (515,"Panpour",11,null,"",50,53,48,64,53,48,5,33,33,33,33);
insert into Pokemon values (514,"Simisear",10,null,"",75,98,63,101,98,63,5,33,33,33,33);
insert into Pokemon values (513,"Pansear",10,null,"",50,53,48,64,53,48,5,33,33,33,33);
insert into Pokemon values (512,"Simisage",12,null,"",75,98,63,101,98,63,5,33,33,33,33);
insert into Pokemon values (511,"Pansage",12,null,"",50,53,48,64,53,48,5,33,33,33,33);
insert into Pokemon values (510,"Liepard",17,null,"",64,88,50,106,88,50,5,33,33,33,33);
insert into Pokemon values (509,"Purrloin",17,null,"",41,50,37,66,50,37,5,33,33,33,33);
insert into Pokemon values (508,"Stoutland",1,null,"",85,110,90,80,45,90,5,33,33,33,33);
insert into Pokemon values (507,"Herdier",1,null,"",65,80,65,60,35,65,5,33,33,33,33);
insert into Pokemon values (506,"Lillipup",1,null,"",45,60,45,55,25,45,5,33,33,33,33);
insert into Pokemon values (505,"Watchog",1,null,"",60,85,69,77,60,69,5,33,33,33,33);
insert into Pokemon values (504,"Patrat",1,null,"",45,55,39,42,35,39,5,33,33,33,33);
insert into Pokemon values (503,"Samurott",11,null,"",95,100,85,70,108,70,5,33,33,33,33);
insert into Pokemon values (502,"Dewott",11,null,"",75,75,60,60,83,60,5,33,33,33,33);
insert into Pokemon values (501,"Oshawott",11,null,"",55,55,45,45,63,45,5,33,33,33,33);
insert into Pokemon values (500,"Emboar",10,2,"",110,123,65,65,100,65,5,33,33,33,33);
insert into Pokemon values (499,"Pignite",10,2,"",90,93,55,55,70,55,5,33,33,33,33);
insert into Pokemon values (498,"Tepig",10,null,"",65,63,45,45,45,45,5,33,33,33,33);
insert into Pokemon values (497,"Serperior",12,null,"",75,75,95,113,75,95,5,33,33,33,33);
insert into Pokemon values (496,"Servine",12,null,"",60,60,75,83,60,75,5,33,33,33,33);
insert into Pokemon values (495,"Snivy",12,null,"",45,45,55,63,45,55,5,33,33,33,33);
insert into Pokemon values (494,"Victini",14,10,"",100,100,100,100,100,100,5,33,33,33,33);




insert into Pokemon values (493,"Arceus",1,null,"",120,120,120,120,120,120,4,33,33,33,33);
insert into Pokemon values (492,"Shaymin",12,null,"",100,100,100,100,100,100,4,33,33,33,33);
insert into Pokemon values (491,"Darkrai",17,null,"",70,90,90,125,135,90,4,33,33,33,33);
insert into Pokemon values (490,"Manaphy",11,null,"",100,100,100,100,100,100,4,33,33,33,33);
insert into Pokemon values (489,"Phione",11,null,"",80,80,80,80,80,80,4,33,33,33,33);
insert into Pokemon values (488,"Cresselia",14,null,"",120,70,120,85,75,130,4,33,33,33,33);
insert into Pokemon values (487,"Giratina",8,16,"",150,100,120,90,100,120,4,33,33,33,33);
insert into Pokemon values (486,"Regigigas",1,null,"",110,160,110,100,80,110,4,33,33,33,33);
insert into Pokemon values (485,"Heatran",10,9,"",91,90,106,77,130,106,4,33,33,33,33);
insert into Pokemon values (484,"Palkia",11,16,"",90,120,100,100,150,120,4,33,33,33,33);
insert into Pokemon values (483,"Dialga",9,16,"",100,120,120,90,150,100,4,33,33,33,33);
insert into Pokemon values (482,"Azelf",14,null,"",75,125,70,115,125,70,4,33,33,33,33);
insert into Pokemon values (481,"Mesprit",14,null,"",80,105,105,80,105,105,4,33,33,33,33);
insert into Pokemon values (480,"Uxie",14,null,"",75,75,130,95,75,130,4,33,33,33,33);
insert into Pokemon values (479,"Rotom",13,8,"",50,50,77,91,95,77,4,33,33,33,33);
insert into Pokemon values (478,"Froslass",15,8,"",70,80,70,110,80,70,4,33,33,33,33);
insert into Pokemon values (477,"Dusknoir",8,null,"",45,100,135,45,65,135,4,33,33,33,33);
insert into Pokemon values (476,"Probopass",6,9,"",60,55,145,40,75,150,4,33,33,33,33);
insert into Pokemon values (475,"Gallade",14,2,"",68,125,65,80,65,115,4,33,33,33,33);
insert into Pokemon values (474,"Porygon-Z",1,null,"",85,80,70,90,135,75,4,33,33,33,33);
insert into Pokemon values (473,"Mamoswine",15,5,"",110,130,80,80,70,60,4,33,33,33,33);
insert into Pokemon values (472,"Gliscor",5,3,"",75,95,125,95,45,75,4,33,33,33,33);
insert into Pokemon values (471,"Glaceon",15,null,"",65,60,110,65,130,95,4,33,33,33,33);
insert into Pokemon values (470,"Leafeon",12,null,"",65,110,130,95,60,65,4,33,33,33,33);
insert into Pokemon values (469,"Yanmega",7,3,"",86,76,86,95,116,56,4,33,33,33,33);
insert into Pokemon values (468,"Togekiss",18,3,"",85,50,95,80,120,115,4,33,33,33,33);
insert into Pokemon values (467,"Magmortar",10,null,"",75,95,67,83,125,95,4,33,33,33,33);
insert into Pokemon values (466,"Electivire",13,null,"",75,123,67,95,95,85,4,33,33,33,33);
insert into Pokemon values (465,"Tangrowth",12,null,"",100,100,125,50,110,50,4,33,33,33,33);
insert into Pokemon values (464,"Rhyperior",5,6,"",115,140,130,40,55,55,4,33,33,33,33);
insert into Pokemon values (463,"Lickilicky",1,null,"",110,85,95,50,80,95,4,33,33,33,33);
insert into Pokemon values (462,"Magnezone",13,9,"",70,70,115,60,130,90,4,33,33,33,33);
insert into Pokemon values (461,"Weavile",17,15,"",70,120,65,125,45,85,4,33,33,33,33);
insert into Pokemon values (460,"Abomasnow",12,15,"",90,92,75,60,92,85,4,33,33,33,33);
insert into Pokemon values (459,"Snover",12,15,"",60,62,50,40,62,60,4,33,33,33,33);
insert into Pokemon values (458,"Mantyke",11,3,"",45,20,50,50,60,120,4,33,33,33,33);
insert into Pokemon values (457,"Lumineon",11,null,"",69,69,76,91,69,86,4,33,33,33,33);
insert into Pokemon values (456,"Finneon",11,null,"",49,49,56,66,49,61,4,33,33,33,33);
insert into Pokemon values (455,"Carnivine",12,null,"",74,100,72,46,90,72,4,33,33,33,33);
insert into Pokemon values (454,"Toxicroak",4,2,"",83,106,65,85,86,65,4,33,33,33,33);
insert into Pokemon values (453,"Croagunk",4,2,"",48,61,40,50,61,40,4,33,33,33,33);
insert into Pokemon values (452,"Drapion",4,17,"",70,90,110,95,60,75,4,33,33,33,33);
insert into Pokemon values (451,"Skorupi",4,7,"",40,50,90,65,30,55,4,33,33,33,33);
insert into Pokemon values (450,"Hippowdon",5,null,"",108,112,118,47,68,72,4,33,33,33,33);
insert into Pokemon values (449,"Hippopotas",5,null,"",68,72,78,32,38,42,4,33,33,33,33);
insert into Pokemon values (448,"Lucario",2,9,"",70,110,70,90,115,70,4,33,33,33,33);
insert into Pokemon values (447,"Riolu",2,null,"",40,70,40,60,35,40,4,33,33,33,33);
insert into Pokemon values (446,"Munchlax",1,null,"",135,85,40,5,40,85,4,33,33,33,33);
insert into Pokemon values (445,"Garchomp",16,5,"",108,130,95,102,80,85,4,33,33,33,33);
insert into Pokemon values (444,"Gabite",16,5,"",68,90,65,82,50,55,4,33,33,33,33);
insert into Pokemon values (443,"Gible",16,5,"",58,70,45,42,40,45,4,33,33,33,33);
insert into Pokemon values (442,"Spiritomb",8,17,"",50,92,108,35,92,108,4,33,33,33,33);
insert into Pokemon values (441,"Chatot",3,1,"",76,65,45,91,92,42,4,33,33,33,33);
insert into Pokemon values (440,"Happiny",1,null,"",100,5,5,30,15,65,4,33,33,33,33);
insert into Pokemon values (439,"Mime Jr.",14,18,"",20,25,45,60,70,90,4,33,33,33,33);
insert into Pokemon values (438,"Bonsly",6,null,"",50,80,95,10,10,45,4,33,33,33,33);
insert into Pokemon values (437,"Bronzong",9,14,"",67,89,116,33,79,116,4,33,33,33,33);
insert into Pokemon values (436,"Bronzor",9,14,"",57,24,86,23,24,86,4,33,33,33,33);
insert into Pokemon values (435,"Skuntank",4,17,"",103,93,67,84,71,61,4,33,33,33,33);
insert into Pokemon values (434,"Stunky",4,17,"",63,63,47,74,41,41,4,33,33,33,33);
insert into Pokemon values (433,"Chingling",14,null,"",45,30,50,45,65,50,4,33,33,33,33);
insert into Pokemon values (432,"Purugly",1,null,"",71,82,64,112,64,59,4,33,33,33,33);
insert into Pokemon values (431,"Glameow",1,null,"",49,55,42,85,42,37,4,33,33,33,33);
insert into Pokemon values (430,"Honchkrow",17,3,"",100,125,52,71,105,52,4,33,33,33,33);
insert into Pokemon values (429,"Mismagius",8,null,"",60,60,60,105,105,105,4,33,33,33,33);
insert into Pokemon values (428,"Lopunny",1,null,"",65,76,84,105,54,96,4,33,33,33,33);
insert into Pokemon values (427,"Buneary",1,null,"",55,66,44,85,44,56,4,33,33,33,33);
insert into Pokemon values (426,"Drifblim",8,3,"",150,80,44,80,90,54,4,33,33,33,33);
insert into Pokemon values (425,"Drifloon",8,3,"",90,50,34,70,60,44,4,33,33,33,33);
insert into Pokemon values (424,"Ambipom",1,null,"",75,100,66,115,60,66,4,33,33,33,33);
insert into Pokemon values (423,"Gastrodon",11,5,"",111,83,68,39,92,82,4,33,33,33,33);
insert into Pokemon values (422,"Shellos",11,null,"",76,48,48,34,57,62,4,33,33,33,33);
insert into Pokemon values (421,"Cherrim",12,null,"",70,60,70,85,87,78,4,33,33,33,33);
insert into Pokemon values (420,"Cherubi",12,null,"",45,35,45,35,62,53,4,33,33,33,33);
insert into Pokemon values (419,"Floatzel",11,null,"",85,105,55,115,85,50,4,33,33,33,33);
insert into Pokemon values (418,"Buizel",11,null,"",55,65,35,85,60,30,4,33,33,33,33);
insert into Pokemon values (417,"Pachirisu",13,null,"",60,45,70,95,45,90,4,33,33,33,33);
insert into Pokemon values (416,"Vespiquen",7,3,"",70,80,102,40,80,102,4,33,33,33,33);
insert into Pokemon values (415,"Combee",7,3,"",30,30,42,70,30,42,4,33,33,33,33);
insert into Pokemon values (414,"Mothim",7,3,"",70,94,50,66,94,50,4,33,33,33,33);
insert into Pokemon values (413,"Wormadam",7,12,"",60,59,85,36,79,105,4,33,33,33,33);
insert into Pokemon values (412,"Burmy",7,null,"",40,29,45,36,29,45,4,33,33,33,33);
insert into Pokemon values (411,"Bastiodon",6,9,"",60,52,168,30,47,138,4,33,33,33,33);
insert into Pokemon values (410,"Shieldon",6,9,"",30,42,118,30,42,88,4,33,33,33,33);
insert into Pokemon values (409,"Rampardos",6,null,"",97,165,60,58,65,50,4,33,33,33,33);
insert into Pokemon values (408,"Cranidos",6,null,"",67,125,40,58,30,30,4,33,33,33,33);
insert into Pokemon values (407,"Roserade",12,4,"",60,70,65,90,125,105,4,33,33,33,33);
insert into Pokemon values (406,"Budew",12,4,"",40,30,35,55,50,70,4,33,33,33,33);
insert into Pokemon values (405,"Luxray",13,null,"",80,120,79,70,95,79,4,33,33,33,33);
insert into Pokemon values (404,"Luxio",13,null,"",60,85,49,60,60,49,4,33,33,33,33);
insert into Pokemon values (403,"Shinx",13,null,"",45,65,34,45,40,34,4,33,33,33,33);
insert into Pokemon values (402,"Kricketune",7,null,"",77,85,51,65,55,51,4,33,33,33,33);
insert into Pokemon values (401,"Kricketot",7,null,"",37,25,41,25,25,41,4,33,33,33,33);
insert into Pokemon values (400,"Bibarel",1,11,"",79,85,60,71,55,60,4,33,33,33,33);
insert into Pokemon values (399,"Bidoof",1,null,"",59,45,40,31,35,40,4,33,33,33,33);
insert into Pokemon values (398,"Staraptor",1,3,"",85,120,70,100,50,60,4,33,33,33,33);
insert into Pokemon values (397,"Staravia",1,3,"",55,75,50,80,40,40,4,33,33,33,33);
insert into Pokemon values (396,"Starly",1,3,"",40,55,30,60,30,30,4,33,33,33,33);
insert into Pokemon values (395,"Empoleon",11,9,"",84,86,88,60,111,101,4,33,33,33,33);
insert into Pokemon values (394,"Prinplup",11,null,"",64,66,68,50,81,76,4,33,33,33,33);
insert into Pokemon values (393,"Piplup",11,null,"",53,51,53,40,61,56,4,33,33,33,33);
insert into Pokemon values (392,"Infernape",10,2,"",76,104,71,108,104,71,4,33,33,33,33);
insert into Pokemon values (391,"Monferno",10,2,"",64,78,52,81,78,52,4,33,33,33,33);
insert into Pokemon values (390,"Chimchar",10,null,"",44,58,44,61,58,44,4,33,33,33,33);
insert into Pokemon values (389,"Torterra",12,5,"",95,109,105,56,75,85,4,33,33,33,33);
insert into Pokemon values (388,"Grotle",12,null,"",75,89,85,36,55,65,4,33,33,33,33);
insert into Pokemon values (387,"Turtwig",12,null,"",55,68,64,31,45,55,4,33,33,33,33);

insert into Pokemon values (386,"Deoxys",14,null,"",50,150,50,150,150,50,3,33,33,33,33);
insert into Pokemon values (385,"Jirachi",9,14,"",100,100,100,100,100,100,3,33,33,33,33);
insert into Pokemon values (384,"Rayquaza",16,3,"",105,150,90,95,150,90,3,33,33,33,33);
insert into Pokemon values (383,"Groudon",5,null,"",100,150,140,90,100,90,3,33,33,33,33);
insert into Pokemon values (382,"Kyogre",11,null,"",100,100,90,90,150,140,3,33,33,33,33);
insert into Pokemon values (381,"Latios",16,14,"",80,90,80,110,130,110,3,33,33,33,33);
insert into Pokemon values (380,"Latias",16,14,"",80,80,90,110,110,130,3,33,33,33,33);
insert into Pokemon values (379,"Registeel",9,null,"",80,75,150,50,75,150,3,33,33,33,33);
insert into Pokemon values (378,"Regice",15,null,"",80,50,100,50,100,200,3,33,33,33,33);
insert into Pokemon values (377,"Regirock",6,null,"",80,100,200,50,50,100,3,33,33,33,33);
insert into Pokemon values (376,"Metagross",9,14,"",80,135,130,70,95,90,3,33,33,33,33);
insert into Pokemon values (375,"Metang",9,14,"",60,75,100,50,55,80,3,33,33,33,33);
insert into Pokemon values (374,"Beldum",9,14,"",40,55,80,30,35,60,3,33,33,33,33);
insert into Pokemon values (373,"Salamence",16,3,"",95,135,80,100,110,80,3,33,33,33,33);
insert into Pokemon values (372,"Shelgon",16,null,"",65,95,100,50,60,50,3,33,33,33,33);
insert into Pokemon values (371,"Bagon",16,null,"",45,75,60,50,40,30,3,33,33,33,33);
insert into Pokemon values (370,"Luvdisc",11,null,"",43,30,55,97,40,65,3,33,33,33,33);
insert into Pokemon values (369,"Relicanth",11,6,"",100,90,130,55,45,65,3,33,33,33,33);
insert into Pokemon values (368,"Gorebyss",11,null,"",55,84,105,52,114,75,3,33,33,33,33);
insert into Pokemon values (367,"Huntail",11,null,"",55,104,105,52,94,75,3,33,33,33,33);
insert into Pokemon values (366,"Clamperl",11,null,"",35,64,85,32,74,55,3,33,33,33,33);
insert into Pokemon values (365,"Walrein",15,11,"",110,80,90,65,95,90,3,33,33,33,33);
insert into Pokemon values (364,"Sealeo",15,11,"",90,60,70,45,75,70,3,33,33,33,33);
insert into Pokemon values (363,"Spheal",15,11,"",70,40,50,25,55,50,3,33,33,33,33);
insert into Pokemon values (362,"Glalie",15,null,"",80,80,80,80,80,80,3,33,33,33,33);
insert into Pokemon values (361,"Snorunt",15,null,"",50,50,50,50,50,50,3,33,33,33,33);
insert into Pokemon values (360,"Wynaut",14,null,"",95,23,48,23,23,48,3,33,33,33,33);
insert into Pokemon values (359,"Absol",17,null,"",65,130,60,75,75,60,3,33,33,33,33);
insert into Pokemon values (358,"Chimecho",14,null,"",75,50,80,65,95,90,3,33,33,33,33);
insert into Pokemon values (357,"Tropius",12,3,"",99,68,83,51,72,87,3,33,33,33,33);
insert into Pokemon values (356,"Dusclops",8,null,"",40,70,130,25,60,130,3,33,33,33,33);
insert into Pokemon values (355,"Duskull",8,null,"",20,40,90,25,30,90,3,33,33,33,33);
insert into Pokemon values (354,"Banette",8,null,"",64,115,65,65,83,63,3,33,33,33,33);
insert into Pokemon values (353,"Shuppet",8,null,"",44,75,35,45,63,33,3,33,33,33,33);
insert into Pokemon values (352,"Kecleon",1,null,"",60,90,70,40,60,120,3,33,33,33,33);
insert into Pokemon values (351,"Castform",1,null,"",70,70,70,70,70,70,3,33,33,33,33);
insert into Pokemon values (350,"Milotic",11,null,"",95,60,79,81,100,125,3,33,33,33,33);
insert into Pokemon values (349,"Feebas",11,null,"",20,15,20,80,10,55,3,33,33,33,33);
insert into Pokemon values (348,"Armaldo",6,7,"",75,125,100,45,70,80,3,33,33,33,33);
insert into Pokemon values (347,"Anorith",6,7,"",45,95,50,75,40,50,3,33,33,33,33);
insert into Pokemon values (346,"cradily",6,12,"",86,81,97,43,81,107,3,33,33,33,33);
insert into Pokemon values (345,"Lileep",6,12,"",66,41,77,23,61,87,3,33,33,33,33);
insert into Pokemon values (344,"Claydol",5,14,"",60,70,105,75,70,120,3,33,33,33,33);
insert into Pokemon values (343,"Baltoy",5,14,"",40,40,55,55,40,70,3,33,33,33,33);
insert into Pokemon values (342,"Crawdaunt",11,17,"",63,120,85,55,90,55,3,33,33,33,33);
insert into Pokemon values (341,"Corphish",11,null,"",43,80,65,35,50,35,3,33,33,33,33);
insert into Pokemon values (340,"Whiscash",11,5,"",110,78,73,60,76,71,3,33,33,33,33);
insert into Pokemon values (339,"Barboach",11,5,"",50,48,43,60,46,41,3,33,33,33,33);
insert into Pokemon values (338,"Solrock",6,14,"",90,95,85,70,55,65,3,33,33,33,33);
insert into Pokemon values (337,"Lunatone",6,14,"",90,55,65,70,95,85,3,33,33,33,33);
insert into Pokemon values (336,"Seviper",4,null,"",73,100,60,65,100,60,3,33,33,33,33);
insert into Pokemon values (335,"Zangoose",1,null,"",73,115,60,90,60,60,3,33,33,33,33);
insert into Pokemon values (334,"Altaria",16,3,"",75,70,90,80,70,105,3,33,33,33,33);
insert into Pokemon values (333,"Swablu",1,3,"",45,40,60,50,40,75,3,33,33,33,33);
insert into Pokemon values (332,"Cacturne",12,17,"",70,115,60,55,115,60,3,33,33,33,33);
insert into Pokemon values (331,"Cacnea",12,null,"",50,85,40,35,85,40,3,33,33,33,33);
insert into Pokemon values (330,"Flygon",5,16,"",80,100,80,100,80,80,3,33,33,33,33);
insert into Pokemon values (329,"Vibrava",5,16,"",50,70,50,70,50,50,3,33,33,33,33);
insert into Pokemon values (328,"Trapinch",5,null,"",45,100,45,10,45,45,3,33,33,33,33);
insert into Pokemon values (327,"Spinda",1,null,"",60,60,60,60,60,60,3,33,33,33,33);
insert into Pokemon values (326,"Grumpig",14,null,"",80,45,65,80,90,110,3,33,33,33,33);
insert into Pokemon values (325,"Spoink",14,null,"",60,25,35,60,70,80,3,33,33,33,33);
insert into Pokemon values (324,"Torkoal",10,null,"",70,85,140,20,85,70,3,33,33,33,33);
insert into Pokemon values (323,"Camerupt",10,5,"",70,100,70,40,105,75,3,33,33,33,33);
insert into Pokemon values (322,"Numel",10,5,"",60,60,40,35,65,45,3,33,33,33,33);
insert into Pokemon values (321,"Wailord",11,null,"",170,90,45,60,90,45,3,33,33,33,33);
insert into Pokemon values (320,"Wailmer",11,null,"",130,70,35,60,70,35,3,33,33,33,33);
insert into Pokemon values (319,"Sharpedo",11,17,"",70,120,40,95,95,40,3,33,33,33,33);
insert into Pokemon values (318,"Carvanha",11,17,"",45,90,20,65,65,20,3,33,33,33,33);
insert into Pokemon values (317,"Swalot",4,null,"",100,73,83,55,73,83,3,33,33,33,33);
insert into Pokemon values (316,"Gulpin",4,null,"",70,43,53,40,43,53,3,33,33,33,33);
insert into Pokemon values (315,"Roselia",12,4,"",50,60,45,65,100,80,3,33,33,33,33);
insert into Pokemon values (314,"Illumise",7,null,"",65,47,75,85,73,85,3,33,33,33,33);
insert into Pokemon values (313,"Volbeat",7,null,"",65,73,75,85,47,85,3,33,33,33,33);
insert into Pokemon values (312,"Minum",13,null,"",60,40,50,95,75,85,3,33,33,33,33);
insert into Pokemon values (311,"Plusle",13,null,"",60,50,40,95,85,75,3,33,33,33,33);
insert into Pokemon values (310,"Manectric",13,null,"",70,75,60,105,105,60,3,33,33,33,33);
insert into Pokemon values (309,"Electrike",13,null,"",40,45,40,65,65,40,3,33,33,33,33);
insert into Pokemon values (308,"Medicham",2,14,"",60,60,75,80,60,75,3,33,33,33,33);
insert into Pokemon values (307,"Meditite",2,14,"",30,40,55,60,40,55,3,33,33,33,33);
insert into Pokemon values (306,"Aggron",9,6,"",70,110,180,50,60,60,3,33,33,33,33);
insert into Pokemon values (305,"Lairon",8,6,"",60,90,140,40,50,50,3,33,33,33,33);
insert into Pokemon values (304,"Aron",9,6,"",50,70,100,30,40,40,3,33,33,33,33);
insert into Pokemon values (303,"Mawile",9,18,"",50,85,85,50,55,55,3,33,33,33,33);
insert into Pokemon values (302,"Sableye",17,8,"",50,75,75,50,65,65,3,33,33,33,33);
insert into Pokemon values (301,"Delcatty",1,null,"",70,65,65,90,55,55,3,33,33,33,33);
insert into Pokemon values (300,"Skitty",1,null,"",50,45,45,50,35,35,3,33,33,33,33);
insert into Pokemon values (299,"Nosepass",6,null,"",30,45,135,30,45,90,3,33,33,33,33);
insert into Pokemon values (298,"Azurill",1,18,"",50,20,40,20,20,40,3,33,33,33,33);
insert into Pokemon values (297,"Hariyama",2,null,"",144,120,60,50,40,60,3,33,33,33,33);
insert into Pokemon values (296,"Makuhita",2,null,"",72,60,30,25,20,30,3,33,33,33,33);
insert into Pokemon values (295,"Exploud",1,null,"",104,91,63,68,91,73,3,33,33,33,33);
insert into Pokemon values (294,"Loudred",1,null,"",84,71,43,48,71,43,3,33,33,33,33);
insert into Pokemon values (293,"Whismur",1,null,"",64,51,23,28,51,23,3,33,33,33,33);
insert into Pokemon values (292,"Shedinja",7,8,"",1,90,45,40,30,30,3,33,33,33,33);
insert into Pokemon values (291,"Ninjask",7,3,"",61,90,45,160,50,50,3,33,33,33,33);
insert into Pokemon values (290,"Nincada",7,5,"",31,45,90,40,30,30,3,33,33,33,33);
insert into Pokemon values (289,"Slaking",1,null,"",150,160,100,100,95,65,3,33,33,33,33);
insert into Pokemon values (288,"Vigoroth",1,null,"",80,80,80,90,55,55,3,33,33,33,33);
insert into Pokemon values (287,"Slakoth",1,null,"",60,60,60,30,35,35,3,33,33,33,33);
insert into Pokemon values (286,"Breloom",12,2,"",60,130,80,70,60,60,3,33,33,33,33);
insert into Pokemon values (285,"Shroomish",12,null,"",60,40,60,35,40,60,3,33,33,33,33);
insert into Pokemon values (284,"Masquerain",7,3,"",70,60,62,80,100,82,3,33,33,33,33);
insert into Pokemon values (283,"Surskit",7,11,"",40,30,32,65,50,52,3,33,33,33,33);
insert into Pokemon values (282,"Gardevoir",14,18,"",68,65,65,80,125,115,3,33,33,33,33);
insert into Pokemon values (281,"Kirlia",14,18,"",38,35,35,50,65,55,3,33,33,33,33);
insert into Pokemon values (280,"Ralts",14,18,"",28,25,25,40,45,35,3,33,33,33,33);
insert into Pokemon values (279,"Pelipper",11,3,"",60,50,100,65,95,70,3,33,33,33,33);
insert into Pokemon values (278,"Wingull",11,3,"",40,30,30,85,55,30,3,33,33,33,33);
insert into Pokemon values (277,"Swellow",1,3,"",60,85,60,125,75,50,3,33,33,33,33);
insert into Pokemon values (276,"Tailow",1,3,"",40,55,30,85,30,30,3,33,33,33,33);
insert into Pokemon values (275,"Shiftry",12,17,"",90,100,60,80,90,60,3,33,33,33,33);
insert into Pokemon values (274,"Nuzleaf",12,17,"",70,70,40,60,60,40,3,33,33,33,33);
insert into Pokemon values (273,"Seedot",12,null,"",40,40,50,30,30,30,3,33,33,33,33);
insert into Pokemon values (272,"Ludicolo",11,12,"",80,70,70,70,90,100,3,33,33,33,33);
insert into Pokemon values (271,"Lombre",11,12,"",60,50,50,50,60,70,3,33,33,33,33);
insert into Pokemon values (270,"Lotad",11,12,"",40,30,30,30,40,50,3,33,33,33,33);
insert into Pokemon values (269,"Dustox",7,4,"",60,50,70,65,50,90,3,33,33,33,33);
insert into Pokemon values (268,"Cascoon",7,null,"",50,35,55,15,25,25,3,33,33,33,33);
insert into Pokemon values (267,"Beautifly",7,3,"",60,70,50,65,100,50,3,33,33,33,33);
insert into Pokemon values (266,"Silcoon",7,null,"",50,35,55,15,25,25,3,33,33,33,33);
insert into Pokemon values (265,"Wurmple",7,null,"",45,45,35,20,20,30,3,33,33,33,33);
insert into Pokemon values (264,"Linoone",1,null,"",78,70,61,100,50,61,3,33,33,33,33);
insert into Pokemon values (263,"Zigzagoon",1,null,"",38,30,41,60,30,41,3,33,33,33,33);
insert into Pokemon values (262,"Mightyena",17,null,"",70,90,70,70,60,60,3,33,33,33,33);
insert into Pokemon values (261,"Poochyena",17,null,"",35,55,35,35,30,30,3,33,33,33,33);
insert into Pokemon values (260,"Swampert",11,5,"",100,110,90,60,85,90,3,33,33,33,33);
insert into Pokemon values (259,"Marshtomp",11,5,"",70,85,70,50,60,70,3,33,33,33,33);
insert into Pokemon values (258,"Mudkip",11,null,"",50,70,50,40,50,50,3,33,33,33,33);
insert into Pokemon values (257,"Blaziken",10,2,"",80,120,70,80,110,70,3,33,33,33,33);
insert into Pokemon values (256,"Combusken",10,2,"",60,85,60,55,85,60,3,33,33,33,33);
insert into Pokemon values (255,"Torchic",10,null,"",45,60,40,45,70,50,3,33,33,33,33);
insert into Pokemon values (254,"Sceptile",12,null,"",70,85,65,120,105,85,3,33,33,33,33);
insert into Pokemon values (253,"Grovyle",12,null,"",50,65,45,95,85,65,3,33,33,33,33);
insert into Pokemon values (252,"Treecko",12,null,"",40,45,35,70,65,55,3,33,33,33,33);





insert into Pokemon values (251,"Celebi",14,12,"",100,100,100,100,100,100,2,33,33,33,33);
insert into Pokemon values (250,"Ho-Oh",10,3,"",106,130,90,90,110,154,2,33,33,33,33);
insert into Pokemon values (249,"Lugia",14,3,"",106,90,130,110,90,154,2,33,33,33,33);
insert into Pokemon values (248,"Tyranitar",6,17,"",100,134,110,61,95,100,2,33,33,33,33);
insert into Pokemon values (247,"Pupitar",6,5,"",70,84,70,51,65,70,2,33,33,33,33);
insert into Pokemon values (246,"Larvitar",6,5,"",50,64,50,41,45,50,2,33,33,33,33);
insert into Pokemon values (245,"Siucune",11,null,"",100,75,115,85,90,115,2,33,33,33,33);
insert into Pokemon values (244,"Entei",10,null,"",115,115,85,100,90,75,2,33,33,33,33);
insert into Pokemon values (243,"Raikou",13,null,"",90,85,75,115,115,100,2,33,33,33,33);
insert into Pokemon values (242,"Blissey",1,null,"",255,10,10,55,75,135,2,33,33,33,33);
insert into Pokemon values (241,"Miltank",1,null,"",95,80,105,100,40,70,2,33,33,33,33);
insert into Pokemon values (240,"Magby",10,null,"",45,75,37,83,70,55,2,33,33,33,33);
insert into Pokemon values (239,"Elekid",13,null,"",45,63,37,95,65,66,2,33,33,33,33);
insert into Pokemon values (238,"Smoochum",15,14,"",45,30,15,65,85,65,2,33,33,33,33);
insert into Pokemon values (237,"Hitmontop",2,null,"",50,95,95,70,35,110,2,33,33,33,33);
insert into Pokemon values (236,"Tyrogue",2,null,"",35,35,35,35,35,35,2,33,33,33,33);
insert into Pokemon values (235,"Smeargle",1,null,"",55,20,35,75,20,45,2,33,33,33,33);
insert into Pokemon values (234,"Stantler",1,null,"",73,95,62,85,85,65,2,33,33,33,33);
insert into Pokemon values (233,"Porygon2",1,null,"",85,80,90,60,105,95,2,33,33,33,33);
insert into Pokemon values (232,"Donphan",5,null,"",90,120,120,50,60,60,2,33,33,33,33);
insert into Pokemon values (231,"Phanpy",5,null,"",90,60,60,40,40,40,2,33,33,33,33);
insert into Pokemon values (230,"Kingdra",11,16,"",75,95,95,85,95,95,2,33,33,33,33);
insert into Pokemon values (229,"Houndoom",17,10,"",75,90,50,95,110,80,2,33,33,33,33);
insert into Pokemon values (228,"Houndour",17,10,"",45,60,30,65,80,50,2,33,33,33,33);
insert into Pokemon values (227,"Skarmory",9,3,"",65,80,140,70,40,70,2,33,33,33,33);
insert into Pokemon values (226,"Mantine",11,3,"",85,40,70,70,80,140,2,33,33,33,33);
insert into Pokemon values (225,"Delibird",15,3,"",45,55,45,75,65,45,2,33,33,33,33);
insert into Pokemon values (224,"Octillery",11,null,"",75,105,75,45,105,75,2,33,33,33,33);
insert into Pokemon values (223,"Remoraid",11,null,"",35,65,35,65,65,35,2,33,33,33,33);
insert into Pokemon values (222,"Corsola",11,6,"",65,55,95,35,65,95,2,33,33,33,33);
insert into Pokemon values (221,"Piloswine",15,5,"",100,100,80,50,60,60,2,33,33,33,33);
insert into Pokemon values (220,"Swinub",15,5,"",50,50,40,50,30,30,2,33,33,33,33);
insert into Pokemon values (219,"Magcargo",10,6,"",60,50,120,30,90,80,2,33,33,33,33);
insert into Pokemon values (218,"Slugma",10,null,"",40,40,40,20,70,40,2,33,33,33,33);
insert into Pokemon values (217,"Ursaring",1,null,"",90,130,75,55,75,75,2,33,33,33,33);
insert into Pokemon values (216,"Teddiursa",1,null,"",60,80,50,40,50,50,2,33,33,33,33);
insert into Pokemon values (215,"Sneasel",17,15,"",55,95,55,115,35,75,2,33,33,33,33);
insert into Pokemon values (214,"Heracross",7,2,"",80,125,75,85,40,95,2,33,33,33,33);
insert into Pokemon values (213,"Shuckle",7,6,"",20,10,230,5,10,230,2,33,33,33,33);
insert into Pokemon values (212,"Scizor",7,9,"",70,130,100,65,55,80,2,33,33,33,33);
insert into Pokemon values (211,"Qwilfish",11,4,"",65,95,85,85,55,55,2,33,33,33,33);
insert into Pokemon values (210,"Granbull",18,null,"",90,120,75,45,60,60,2,33,33,33,33);
insert into Pokemon values (209,"Snubbul",18,null,"",60,80,50,30,40,40,2,33,33,33,33);
insert into Pokemon values (208,"Steelix",9,5,"",75,85,200,30,55,65,2,33,33,33,33);
insert into Pokemon values (207,"Gligar",5,3,"",65,75,105,85,35,65,2,33,33,33,33);
insert into Pokemon values (206,"Dunsparce",1,null,"",100,70,70,45,65,65,2,33,33,33,33);
insert into Pokemon values (205,"Forretress",7,9,"",75,90,140,40,60,60,2,33,33,33,33);
insert into Pokemon values (204,"Pineco",7,null,"",50,65,90,15,35,35,2,33,33,33,33);
insert into Pokemon values (203,"Girafarig",1,14,"",70,80,65,85,90,65,2,33,33,33,33);
insert into Pokemon values (202,"Wobbuffet",14,null,"",190,33,58,33,33,58,2,33,33,33,33);
insert into Pokemon values (201,"Unnown",14,null,"",48,72,48,48,72,48,2,33,33,33,33);
insert into Pokemon values (200,"Misdreavus",8,null,"",60,60,60,85,85,85,2,33,33,33,33);
insert into Pokemon values (199,"Slowking",11,18,"",95,75,80,30,100,110,2,33,33,33,33);
insert into Pokemon values (198,"Murkrow",17,3,"",60,85,42,91,85,42,2,33,33,33,33);
insert into Pokemon values (197,"Umbreon",17,null,"",95,65,110,65,60,130,2,33,33,33,33);
insert into Pokemon values (196,"Espeon",14,null,"",65,65,60,110,130,95,2,33,33,33,33);
insert into Pokemon values (195,"Quagsire",11,5,"",95,85,85,35,65,65,2,33,33,33,33);
insert into Pokemon values (194,"Wooper",11,5,"",55,45,45,15,25,25,2,33,33,33,33);
insert into Pokemon values (193,"Yanma",7,3,"",65,65,45,95,75,45,2,33,33,33,33);
insert into Pokemon values (192,"Sunflora",12,null,"",75,75,55,30,105,85,2,33,33,33,33);
insert into Pokemon values (191,"Sunkern",12,null,"",30,30,30,30,30,30,2,33,33,33,33);
insert into Pokemon values (190,"Aipom",1,null,"",55,70,55,85,40,55,2,33,33,33,33);
insert into Pokemon values (189,"Jumpluff",12,3,"",75,55,70,110,55,95,2,33,33,33,33);
insert into Pokemon values (188,"Skiploom",12,3,"",55,45,50,80,45,65,2,33,33,33,33);
insert into Pokemon values (187,"Hoppip",12,3,"",35,35,40,50,35,55,2,33,33,33,33);
insert into Pokemon values (186,"Politoed",11,null,"",90,75,75,70,90,100,2,33,33,33,33);
insert into Pokemon values (185,"Sudowoodo",6,null,"",70,100,115,30,30,65,2,33,33,33,33);
insert into Pokemon values (184,"Azumarill",11,18,"",100,50,80,50,60,80,2,33,33,33,33);
insert into Pokemon values (183,"Marill",11,18,"",70,20,50,40,20,50,2,33,33,33,33);
insert into Pokemon values (182,"Bellossom",12,null,"",75,80,95,50,90,100,2,33,33,33,33);
insert into Pokemon values (181,"Ampharos",13,null,"",90,75,85,55,115,90,2,33,33,33,33);
insert into Pokemon values (180,"Flaaffy",13,null,"",70,55,55,45,80,60,2,33,33,33,33);
insert into Pokemon values (179,"Mareep",13,null,"",55,40,40,35,65,45,2,33,33,33,33);
insert into Pokemon values (178,"Xatu",14,3,"",65,75,70,95,95,70,2,33,33,33,33);
insert into Pokemon values (177,"Natu",14,3,"",40,50,45,70,70,45,2,33,33,33,33);
insert into Pokemon values (176,"Togetic",18,3,"",55,40,85,40,80,105,2,33,33,33,33);
insert into Pokemon values (175,"Togepi",18,null,"",35,20,65,20,40,65,2,33,33,33,33);
insert into Pokemon values (174,"Igglybuff",1,18,"",90,30,15,15,40,20,2,33,33,33,33);
insert into Pokemon values (173,"Cleffa",18,null,"",50,25,28,15,45,55,2,33,33,33,33);
insert into Pokemon values (172,"Pichu",13,null,"",20,40,15,60,35,35,2,33,33,33,33);
insert into Pokemon values (171,"Lanturn",11,13,"",125,58,58,67,76,76,2,33,33,33,33);
insert into Pokemon values (170,"Chinchou",11,13,"",75,38,38,67,56,56,2,33,33,33,33);
insert into Pokemon values (169,"Crobat",4,3,"",85,90,80,130,70,80,2,33,33,33,33);
insert into Pokemon values (168,"Ariados",7,3,"",70,90,70,40,60,70,2,33,33,33,33);
insert into Pokemon values (167,"Spinarak",7,3,"",40,60,40,30,40,40,2,33,33,33,33);
insert into Pokemon values (166,"Ledian",7,3,"",55,35,50,85,55,110,2,33,33,33,33);
insert into Pokemon values (165,"Ledyba",7,3,"",40,20,30,55,40,80,2,33,33,33,33);
insert into Pokemon values (164,"Noctowl",1,3,"",100,50,50,70,86,96,2,33,33,33,33);
insert into Pokemon values (163,"Hoothoot",1,3,"",60,30,30,50,36,56,2,33,33,33,33);
insert into Pokemon values (162,"Furret",1,null,"",85,76,64,90,45,55,2,33,33,33,33);
insert into Pokemon values (161,"Sentret",1,null,"",35,46,34,20,35,45,2,33,33,33,33);
insert into Pokemon values (160,"Feraligatr",11,null,"",85,105,100,78,79,83,2,33,33,33,33);
insert into Pokemon values (159,"Croconaw",11,null,"",65,80,80,58,59,63,2,33,33,33,33);
insert into Pokemon values (158,"Totodile",11,null,"",50,65,64,43,44,48,2,33,33,33,33);
insert into Pokemon values (157,"Typhlosion",10,null,"",78,84,78,100,109,85,2,33,33,33,33);
insert into Pokemon values (156,"Quilava",10,null,"",58,64,58,80,80,65,2,33,33,33,33);
insert into Pokemon values (155,"Cyndaquil",10,null,"",39,52,43,65,60,50,2,33,33,33,33);
insert into Pokemon values (154,"Meganium",12,null,"",80,82,100,80,83,100,2,33,33,33,33);
insert into Pokemon values (153,"Bayleef",12,null,"",60,62,80,60,63,80,2,33,33,33,33);
insert into Pokemon values (152,"Chikorita",12,null,"",45,49,65,45,49,65,2,33,33,33,33);



insert into Pokemon values (151,"Mew",14,null,"",100,100,100,100,100,100,1,33,33,33,33);
insert into Pokemon values (150,"Mewtwo",14,null,"",106,110,90,130,154,90,1,33,33,33,33);
insert into Pokemon values (149,"Dragonite",16,3,"",91,134,95,80,100,100,1,33,33,33,33);
insert into Pokemon values (148,"Dragonair",16,null,"",61,84,65,70,70,70,1,33,33,33,33);
insert into Pokemon values (147,"Dratini",16,null,"",41,64,45,50,50,50,1,33,33,33,33);
insert into Pokemon values (146,"Moltres",10,3,"",90,100,90,90,125,85,1,33,33,33,33);
insert into Pokemon values (145,"Zapdos",13,3,"",90,85,100,85,95,125,1,33,33,33,33);
insert into Pokemon values (144,"Articuno",15,3,"",90,85,100,85,95,125,1,33,33,33,33);
insert into Pokemon values (143,"Snorlax",1,null,"",160,110,65,30,65,110,1,33,33,33,33);
insert into Pokemon values (142,"Aerodactyl",6,3,"",80,105,65,130,60,75,1,33,33,33,33);
insert into Pokemon values (141,"Kabutops",6,11,"",60,115,105,80,65,70,1,33,33,33,33);
insert into Pokemon values (140,"Kabuto",6,11,"",30,80,90,55,55,45,1,33,33,33,33);
insert into Pokemon values (139,"Omastar",6,11,"",70,60,125,55,115,70,1,33,33,33,33);
insert into Pokemon values (138,"Omanyte",6,11,"",35,40,100,35,90,55,1,33,33,33,33);
insert into Pokemon values (137,"Porygon",1,null,"",65,60,70,40,85,75,1,33,33,33,33);
insert into Pokemon values (136,"Flareon",10,null,"",65,130,60,65,95,110,1,33,33,33,33);
insert into Pokemon values (135,"Jolteon",13,null,"",65,65,60,130,110,95,1,33,33,33,33);
insert into Pokemon values (134,"Vaporeon",11,null,"",130,65,60,65,110,95,1,33,33,33,33);
insert into Pokemon values (133,"Eevee",1,null,"",55,55,50,55,45,65,1,33,33,33,33);
insert into Pokemon values (132,"Ditto",1,null,"",48,48,48,48,48,48,1,33,33,33,33);
insert into Pokemon values (131,"Lapras",11,15,"",120,85,80,60,85,95,1,33,33,33,33);
insert into Pokemon values (130,"Gyarados",11,3,"",95,125,79,81,60,100,1,33,33,33,33);
insert into Pokemon values (129,"Magikarp",11,null,"",20,10,55,80,15,20,1,33,33,33,33);
insert into Pokemon values (128,"Tauros",1,null,"",75,100,95,110,40,70,1,33,33,33,33);
insert into Pokemon values (127,"Pinsir",7,null,"",65,125,100,85,55,70,1,33,33,33,33);
insert into Pokemon values (126,"Magmar",10,null,"",65,95,57,93,100,85,1,33,33,33,33);
insert into Pokemon values (125,"Electabuzz",13,null,"",65,83,57,105,95,85,1,33,33,33,33);
insert into Pokemon values (124,"Jynx",15,14,"",65,50,35,95,115,95,1,33,33,33,33);
insert into Pokemon values (123,"Scyther",7,3,"",70,110,80,105,55,80,1,33,33,33,33);
insert into Pokemon values (122,"Mr.Mime",14,18,"",40,45,65,90,100,120,1,33,33,33,33);
insert into Pokemon values (121,"Starmie",11,14,"",60,75,85,115,100,85,1,33,33,33,33);
insert into Pokemon values (120,"Staryu",11,null,"",30,45,55,85,70,55,1,33,33,33,33);
insert into Pokemon values (119,"Seaking",11,null,"",80,92,65,68,65,80,1,33,33,33,33);
insert into Pokemon values (118,"Goldeen",11,null,"",45,67,60,63,35,50,1,33,33,33,33);
insert into Pokemon values (117,"Seadra",11,null,"",55,65,95,85,95,45,1,33,33,33,33);
insert into Pokemon values (116,"Horsea",11,null,"",30,40,70,60,70,25,1,33,33,33,33);
insert into Pokemon values (115,"Kangaskhan",1,null,"",105,95,80,90,40,80,1,33,33,33,33);
insert into Pokemon values (114,"Tangela",12,null,"",65,55,115,60,100,40,1,33,33,33,33);
insert into Pokemon values (113,"Chansey",1,null,"",250,5,5,50,35,105,1,33,33,33,33);
insert into Pokemon values (112,"Rhydon",6,5,"",105,130,120,40,45,45,1,33,33,33,33);
insert into Pokemon values (111,"Rhyhorn",6,5,"",80,85,95,25,30,30,1,33,33,33,33);
insert into Pokemon values (110,"Weezing",4,null,"",65,90,120,60,85,70,1,33,33,33,33);
insert into Pokemon values (109,"Koffing",4,null,"",40,65,95,35,60,45,1,33,33,33,33);
insert into Pokemon values (108,"Lickitung",1,null,"",90,55,75,30,60,75,1,33,33,33,33);
insert into Pokemon values (107,"Hitmonchan",2,null,"",50,105,79,76,35,110,1,33,33,33,33);
insert into Pokemon values (106,"Hitmonlee",2,null,"",50,120,53,87,35,110,1,33,33,33,33);
insert into Pokemon values (105,"Marowak",5,null,"",60,80,110,45,50,80,1,33,33,33,33);
insert into Pokemon values (104,"Cubone",5,null,"",50,50,95,35,40,50,1,33,33,33,33);
insert into Pokemon values (103,"Exeggutor",12,14,"",95,95,85,55,125,75,1,33,33,33,33);
insert into Pokemon values (102,"Exeggcute",12,14,"",60,40,80,40,60,45,1,33,33,33,33);
insert into Pokemon values (101,"ELectrode",13,null,"",60,50,70,150,80,80,1,33,33,33,33);
insert into Pokemon values (100,"Voltorb",13,null,"",40,30,50,100,55,55,1,33,33,33,33);
insert into Pokemon values (99,"Kingler",11,null,"",55,130,115,75,50,50,1,33,33,33,33);
insert into Pokemon values (98,"Krabby",11,null,"",30,105,90,50,25,25,1,33,33,33,33);
insert into Pokemon values (97,"Hypno",14,null,"",85,73,70,67,73,115,1,33,33,33,33);
insert into Pokemon values (96,"Drowzee",14,null,"",60,48,45,42,43,90,1,33,33,33,33);
insert into Pokemon values (95,"Onix",6,5,"",35,45,160,70,30,45,1,33,33,33,33);
insert into Pokemon values (94,"Gengar",8,4,"",60,65,60,110,130,75,1,33,33,33,33);
insert into Pokemon values (93,"Haunter",8,4,"",45,50,45,95,115,55,1,33,33,33,33);
insert into Pokemon values (92,"Gastly",8,4,"",30,35,30,80,100,35,1,33,33,33,33);
insert into Pokemon values (91,"Cloyster",11,15,"",50,95,180,70,85,45,1,33,33,33,33);
insert into Pokemon values (90,"Shellder",11,null,"",30,65,100,40,45,25,1,33,33,33,33);
insert into Pokemon values (89,"Muk",4,null,"",105,105,75,50,65,100,1,33,33,33,33);
insert into Pokemon values (88,"Grimer",4,null,"",80,80,50,25,40,50,1,33,33,33,33);
insert into Pokemon values (87,"Dewgong",11,15,"",90,70,80,70,70,95,1,33,33,33,33);
insert into Pokemon values (86,"Seel",11,null,"",65,45,55,45,45,70,1,33,33,33,33);
insert into Pokemon values (85,"DOdrio",1,3,"",60,110,70,110,60,60,1,33,33,33,33);
insert into Pokemon values (84,"Doduo",1,3,"",35,85,45,75,35,35,1,33,33,33,33);
insert into Pokemon values (83,"Farfetch´d",1,3,"",52,90,55,60,58,62,1,33,33,33,33);
insert into Pokemon values (82,"Magneton",13,9,"",50,60,95,70,120,70,1,33,33,33,33);
insert into Pokemon values (81,"Magnemite",13,9,"",25,35,70,45,95,55,1,33,33,33,33);
insert into Pokemon values (80,"Slowbro",11,14,"",95,75,110,30,100,80,1,33,33,33,33);
insert into Pokemon values (79,"Slowpoke",11,14,"",90,65,65,15,40,40,1,33,33,33,33);
insert into Pokemon values (78,"Rapidash",10,null,"",65,100,70,105,80,80,1,33,33,33,33);
insert into Pokemon values (77,"Ponyta",10,null,"",50,85,55,90,65,65,1,33,33,33,33);
insert into Pokemon values (76,"Golem",6,5,"",80,120,130,45,55,65,1,33,33,33,33);
insert into Pokemon values (75,"Graveler",6,5,"",55,95,115,35,45,45,1,33,33,33,33);
insert into Pokemon values (74,"Geodude",6,5,"",40,80,100,20,30,30,1,33,33,33,33);
insert into Pokemon values (73,"Tentacruel",11,4,"",80,70,65,100,80,120,1,33,33,33,33);
insert into Pokemon values (72,"Tentacool",11,4,"",40,40,35,70,50,100,1,33,33,33,33);
insert into Pokemon values (71,"Victreebel",12,4,"",80,105,65,70,100,70,1,33,33,33,33);
insert into Pokemon values (70,"Weepinbell",12,4,"",65,90,50,55,85,45,1,33,33,33,33);
insert into Pokemon values (69,"Bellsprout",12,4,"",50,75,35,40,70,30,1,33,33,33,33);
insert into Pokemon values (68,"Machamp",2,null,"",90,130,80,55,65,85,1,33,33,33,33);
insert into Pokemon values (67,"Machoke",2,null,"",80,100,70,45,50,60,1,33,33,33,33);
insert into Pokemon values (66,"Machop",2,null,"",70,80,50,35,35,35,1,33,33,33,33);
insert into Pokemon values (65,"Alakazam",14,null,"",55,50,45,120,135,95,1,33,33,33,33);
insert into Pokemon values (64,"Kadabra",14,null,"",40,35,30,105,120,70,1,33,33,33,33);
insert into Pokemon values (63,"Abra",14,null,"",25,20,15,90,105,55,1,33,33,33,33);
insert into Pokemon values (62,"Poliwrath",11,2,"",90,95,95,70,70,90,1,33,33,33,33);
insert into Pokemon values (61,"Poliwhirl",11,null,"",65,65,65,90,50,50,1,33,33,33,33);
insert into Pokemon values (60,"Poliwag",11,null,"",40,50,40,90,40,40,1,33,33,33,33);
insert into Pokemon values (59,"Arcanine",10,null,"",90,110,80,95,100,80,1,33,33,33,33);
insert into Pokemon values (58,"Growlithe",10,null,"",55,70,45,60,70,50,1,33,33,33,33);
insert into Pokemon values (57,"Primeape",2,null,"",65,105,60,95,60,70,1,33,33,33,33);
insert into Pokemon values (56,"Mankey",2,null,"",40,80,35,70,35,45,1,33,33,33,33);
insert into Pokemon values (55,"Golduck",11,null,"",80,82,78,85,95,80,1,33,33,33,33);
insert into Pokemon values (54,"Psyduck",11,null,"",50,52,48,55,65,50,1,33,33,33,33);
insert into Pokemon values (53,"Persian",1,null,"",65,70,60,115,65,65,1,33,33,33,33);
insert into Pokemon values (52,"Meowth",1,null,"",40,45,35,90,40,40,1,33,33,33,33);
insert into Pokemon values (51,"Dugtrio",4,null,"",35,100,50,120,50,70,1,33,33,33,33);
insert into Pokemon values (50,"Diglett",5,null,"",10,55,25,95,35,45,1,33,33,33,33);
insert into Pokemon values (49,"Venomoth",7,4,"",70,65,60,90,90,75,1,33,33,33,33);
insert into Pokemon values (48,"Venonat",7,4,"",60,55,50,45,40,55,1,33,33,33,33);
insert into Pokemon values (47,"Parasect",7,12,"",60,95,80,30,60,80,1,33,33,33,33);
insert into Pokemon values (46,"Paras",7,12,"",35,70,55,25,45,55,1,33,33,33,33);
insert into Pokemon values (45,"Vileplume",12,4,"",75,80,85,50,110,90,1,33,33,33,33);
insert into Pokemon values (44,"Gloom",12,4,"",60,65,70,40,85,75,1,33,33,33,33);
insert into Pokemon values (43,"Oddish",12,4,"",45,50,55,30,75,65,1,33,33,33,33);
insert into Pokemon values (42,"Golbat",4,3,"",75,80,70,90,65,75,1,33,33,33,33);
insert into Pokemon values (41,"Zubat",4,3,"",40,45,35,55,30,40,1,33,33,33,33);
insert into Pokemon values (40,"Wigglypuff",1,18,"",140,70,45,45,85,50,1,33,33,33,33);
insert into Pokemon values (39,"Jigglypuff",1,18,"",115,45,20,20,45,25,1,33,33,33,33);
insert into Pokemon values (38,"Ninetales",10,null,"",73,76,75,100,81,100,1,33,33,33,33);
insert into Pokemon values (37,"Vulpix",10,null,"",38,41,40,65,50,65,1,33,33,33,33);
insert into Pokemon values (36,"Clefable",18,null,"",95,70,73,60,95,90,1,33,33,33,33);
insert into Pokemon values (35,"Clefairy",18,null,"",70,45,48,35,60,65,1,33,33,33,33);
insert into Pokemon values (34,"Nidoking",4,5,"",81,102,77,85,85,75,1,33,33,33,33);
insert into Pokemon values (33,"Nidorino",4,null,"",61,72,57,65,55,55,1,33,33,33,33);
insert into Pokemon values (32,"Nidoran♂",4,null,"",46,75,40,50,40,40,1,33,33,33,33);
insert into Pokemon values (31,"Nidoqueen",4,5,"",90,92,87,76,75,85,1,33,33,33,33);
insert into Pokemon values (30,"Nidorina",4,null,"",70,62,67,56,55,55,1,33,33,33,33);
insert into Pokemon values (29,"Nidoran♀",4,null,"",55,47,52,41,40,40,1,33,33,33,33);
insert into Pokemon values (28,"Sandslash",5,null,"",75,100,110,65,45,55,1,33,33,33,33);
insert into Pokemon values (27,"Sandsshrew",5,null,"",50,75,85,40,20,30,1,33,33,33,33);
insert into Pokemon values (26,"Raichu",13,null,"",60,90,55,110,90,80,1,33,33,33,33);
insert into Pokemon values (25,"Pikachu",13,null,"",35,55,40,90,50,50,1,33,33,33,33);
insert into Pokemon values (24,"Arbok",4,null,"",60,95,69,80,65,79,1,33,33,33,33);
insert into Pokemon values (23,"Ekans",4,null,"",35,60,44,55,40,54,1,33,33,33,33);
insert into Pokemon values (22,"Fearow",1,3,"",65,90,65,100,61,61,1,33,33,33,33);
insert into Pokemon values (21,"Spearow",1,3,"",40,60,30,70,31,31,1,33,33,33,33);
insert into Pokemon values (20,"Raticate",1,null,"",55,81,60,97,50,70,1,33,33,33,33);
insert into Pokemon values (19,"Rattata",1,null,"",30,56,35,72,25,35,1,33,33,33,33);
insert into Pokemon values (18,"Pidgeot",1,3,"",83,80,75,101,70,70,1,33,33,33,33);
insert into Pokemon values (17,"Pidgeotto",1,3,"",63,60,55,71,50,50,1,33,33,33,33);
insert into Pokemon values (16,"Pidgey",1,3,"",40,45,40,56,35,35,1,33,33,33,33);
Insert into Pokemon values(1,"Bulbasaur",12,4,"",45,49,49,45,65,65,1,33,33,33,33);
Insert into Pokemon values(2,"Ivysaur",12,4," ",60,62,63,60,80,80,1,33,33,33,33);
Insert into Pokemon values(3,"Venusaur",12,4," ",80,82,83,80,100,100,1,33,33,33,33);
Insert into Pokemon values(4,"Charmander",10,null," ",39,52,43,65,60,50,1,33,33,33,33);
Insert into Pokemon values(5,"Charmeleon",10,null," ",58,64,58,80,80,65,1,33,33,33,33);
Insert into Pokemon values(6,"Charizard",10,3," ",78,84,78,100,109,85,1,33,33,33,33);
Insert into Pokemon values(7,"Squirtle",12,null," ",59,63,80,58,65,80,1,33,33,33,33);
Insert into Pokemon values(8,"Wartortle",12,null," ",79,83,100,78,85,105,1,33,33,33,33);
Insert into Pokemon values(9,"Blastoise",12,null," ",79,83,100,78,85,105,1,33,33,33,33);
Insert into Pokemon values(10,"Caterpie",7,null," ",45,30,35,45,20,20,1,33,33,33,33);
Insert into Pokemon values(11,"Metapod",7,null," ",50,20,55,30,25,25,1,33,33,33,33);
Insert into Pokemon values(12,"Butterfree",7,3," ",60,45,50,70,90,80,1,33,33,33,33);
Insert into Pokemon values(13,"Weedle",7,null," ",40,35,30,50,20,20,1,33,33,33,33);
Insert into Pokemon values(14,"Kakuna",7,null," ",45,25,50,35,25,25,1,33,33,33,33);
Insert into Pokemon values(15,"Beedrill",7,4," ",65,90,40,75,45,80,1,33,33,33,33);

/* crear una vista de los stats totales  */ 
create view statsTotales as Select poke_name 'Nombre', sum(hp+atk+def+spAtk+spDef+vel) 'Stats' from Pokemon group by poke_name;
/* crear vista de los pokemons por generacion */ 
create view pokeGen as Select poke_gen, count(poke_id) 'Cantidad' from Pokemon group by poke_gen;
/* crear vista de la potencia media de los movimientos segun su tipo*/
create view mov_potencia_media as select type_name 'Tipo', truncate(avg(potency),0)'Potencia media' from Movimiento join Tipo on mov_type = type_id group by type_name;


create trigger AltaUsuario 
after insert on usuario for each row
insert into pc (user_login) values (new.user_login);


delimiter //
create procedure cargarCajas (id int) 
begin
declare i int default 0;
while i < 8
	do insert into Caja (pc_id) values(id);
	set i = i + 1;
	end while;
end;
//

delimiter //
create procedure cargarEquipo (idP int, login varchar(30)) 
begin
insert into Equipo (user_login, poke_id1) values (login, idP);
end;
//


create trigger crearCajas 
after insert on pc for each row
call cargarCajas(new.pc_id);

insert into Usuario (user_login, user_name, user_pass) values ('ass23','as','as');









drop role if exists rolProfesor;
CREATE ROLE rolProfesor;
GRANT ALL PRIVILEGES on pcpokemon.* to rolProfesor;

create user prof_oak identified by  'kantokanto',
 prof_elm identified by  'jhotojhoto',
 prof_abedul identified by 'hoennhoenn',
 prof_serbal identified by 'sinnohsinnoh',
 prof_encina identified by 'unovaunova'
 default role rolProfesor;

drop role if exists rolJugador;
CREATE ROLE rolJugador;
grant SELECT, DELETE, INSERT, UPDATE on pcpokemon.* to rolJugador;
/* SHOW DATABASES se hace con el *   */


create user jugador1 identified by 'Igor',
jugador2 identified by 'Unai',
jugador3 identified by 'Clemente',
jugador4 identified by 'Rojo' default role rolJugador;



/* AL REGISTRAR USUARIO -> CREAR TRIGGER PARA INTRODUCIR UN POKEMON EN LA BDD DE EQUIPO ,
HACER UNA CAJA PARA EL USUARIO, PC, Y CAJAPOKEMON   
drop table equipo;
drop table cajapokemon;
drop table Pokemon;

SELECT poke_id, poke_name,T1.type_name Tipo1 ,T2.type_name Tipo2,descripcion,hp,atk,def,vel,spAtk,spDef,poke_gen from pokemon join tipos T1 on poke_type1=T1.type_id join tipos T2 on poke_type2=T2.type_id where poke_id=1 ;*/