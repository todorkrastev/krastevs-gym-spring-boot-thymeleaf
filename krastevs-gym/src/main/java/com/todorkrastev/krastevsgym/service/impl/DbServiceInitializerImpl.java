package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.entity.*;
import com.todorkrastev.krastevsgym.model.enums.EquipmentTypeEnum;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import com.todorkrastev.krastevsgym.model.enums.PictureCategoryEnum;
import com.todorkrastev.krastevsgym.model.enums.UserRoleEnum;
import com.todorkrastev.krastevsgym.repository.*;
import com.todorkrastev.krastevsgym.service.DbServiceInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

import static com.todorkrastev.krastevsgym.model.enums.UserRoleEnum.ADMIN;

@Service
public class DbServiceInitializerImpl implements DbServiceInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbServiceInitializerImpl.class);

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseCategoryRepository exerciseCategoryRepository;
    private final PictureRepository pictureRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;
    private final String adminPass;

    public DbServiceInitializerImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ExerciseRepository exerciseRepository, ExerciseCategoryRepository exerciseCategoryRepository, PictureRepository pictureRepository, ProductRepository productRepository, PasswordEncoder passwordEncoder, @Value("${app.default.admin.password}") String adminPass) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.exerciseRepository = exerciseRepository;
        this.exerciseCategoryRepository = exerciseCategoryRepository;
        this.pictureRepository = pictureRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminPass = adminPass;
    }

    @Override
    public void init() {
        LOGGER.info("===================Database startup begins===================");

        if (userRepository.count() == 0 & userRoleRepository.count() == 0) {

            UserRoleEntity adminRole = new UserRoleEntity().setRole(ADMIN);
            UserRoleEntity moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            UserRoleEntity savedAdminRole = userRoleRepository.save(adminRole);
            UserRoleEntity savedModeratorRole = userRoleRepository.save(moderatorRole);
            UserRoleEntity savedUserRole = userRoleRepository.save(userRole);

            initAdmin(List.of(savedAdminRole));
            initModerator(List.of(savedModeratorRole));
            initUser(List.of(savedUserRole));
        }

        if (exerciseCategoryRepository.count() == 0 & exerciseRepository.count() == 0) {
            exerciseCategoryRepository.saveAll(createExerciseCategoryEntities());
            exerciseRepository.saveAll(createExerciseEntities());
        }


        if (pictureRepository.count() == 0 && productRepository.count() == 0) {
            productRepository.saveAll(createProductEntities());
        }

        LOGGER.info("===================Database startup ends===================");
    }

    private List<ProductEntity> createProductEntities() {
        LOGGER.info("===================Creating products and pictures===================");
        final UserEntity ADMIN = userRepository.findAdminByCategory(UserRoleEnum.ADMIN);

        PictureCategoryEnum MEN = PictureCategoryEnum.MEN;
        PictureCategoryEnum WOMEN = PictureCategoryEnum.WOMEN;

        return List.of(
                new ProductEntity()
                        .setName("Pink Top")
                        .setDescription("Fitted sleeveless stretch crop top with a round neckline.")
                        .setPrice(BigDecimal.valueOf(15.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Pink Top")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724874/krastevs-gym/imgs/shop/shop-women/top-pink_zntgi0.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Grey Top")
                        .setDescription("Fitted sleeveless stretch crop top with a round neckline.")
                        .setPrice(BigDecimal.valueOf(15.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Grey Top")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724872/krastevs-gym/imgs/shop/shop-women/top-grey_neb6dt.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Green Top")
                        .setDescription("Fitted sleeveless stretch crop top with a round neckline.")
                        .setPrice(BigDecimal.valueOf(15.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Green Top")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724871/krastevs-gym/imgs/shop/shop-women/top-green_e4yzmz.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Burgundy Top")
                        .setDescription("Fitted sleeveless stretch crop top with a round neckline.")
                        .setPrice(BigDecimal.valueOf(15.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Burgundy Top")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724869/krastevs-gym/imgs/shop/shop-women/top-burgundy_xgpclh.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Brown Top")
                        .setDescription("Fitted sleeveless stretch crop top with a round neckline.")
                        .setPrice(BigDecimal.valueOf(15.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Brown Top")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724868/krastevs-gym/imgs/shop/shop-women/top-brown_jgnxue.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Black Top")
                        .setDescription("Fitted sleeveless stretch crop top with a round neckline.")
                        .setPrice(BigDecimal.valueOf(15.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Black Top")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724865/krastevs-gym/imgs/shop/shop-women/top-black_ftf2tk.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("White Sports Socks")
                        .setDescription("Elastic knit socks with ribbed elastic trims.")
                        .setPrice(BigDecimal.valueOf(5.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("White Sports Socks")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724865/krastevs-gym/imgs/shop/shop-women/socks-white_wjv0cc.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("White sports sneakers")
                        .setDescription("Trainers with a flat form sole. Lace-up fastening.")
                        .setPrice(BigDecimal.valueOf(179.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("White Sneakers")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724862/krastevs-gym/imgs/shop/shop-women/sneakers-white_v80hrc.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Pink Shorts")
                        .setDescription("Mid-rise shorts with an adjustable inner drawstring.")
                        .setPrice(BigDecimal.valueOf(29.99))
                        .setCategory(WOMEN)
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Pink Shorts")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724861/krastevs-gym/imgs/shop/shop-women/shorts-pink_msddgt.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Grey Shorts")
                        .setDescription("Mid-rise shorts with an adjustable inner drawstring.")
                        .setPrice(BigDecimal.valueOf(29.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Grey Shorts")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724860/krastevs-gym/imgs/shop/shop-women/shorts-grey_w1ynqv.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Green Shorts")
                        .setDescription("Mid-rise shorts with an adjustable inner drawstring.")
                        .setPrice(BigDecimal.valueOf(29.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Green Shorts")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724859/krastevs-gym/imgs/shop/shop-women/shorts-green_jxw63j.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Burgundy Shorts")
                        .setDescription("Mid-rise shorts with an adjustable inner drawstring.")
                        .setPrice(BigDecimal.valueOf(29.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Burgundy Shorts")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724857/krastevs-gym/imgs/shop/shop-women/shorts-burgundy_co1qan.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Brown Shorts")
                        .setDescription("Mid-rise shorts with an adjustable inner drawstring.")
                        .setPrice(BigDecimal.valueOf(29.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Brown Shorts")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724856/krastevs-gym/imgs/shop/shop-women/shorts-brown_jp33tp.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Pink Cycling Pants")
                        .setDescription("High-waist cycling pants.")
                        .setPrice(BigDecimal.valueOf(39.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Pink Cycling Pants")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724855/krastevs-gym/imgs/shop/shop-women/cycling-pants-pink_ncsgkt.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Grey Cycling Pants")
                        .setDescription("High-waist cycling pants.")
                        .setPrice(BigDecimal.valueOf(39.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Grey Cycling Pants")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724854/krastevs-gym/imgs/shop/shop-women/cycling-pants-grey_qc0rlq.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Green Cycling Pants")
                        .setDescription("High-waist cycling pants.")
                        .setPrice(BigDecimal.valueOf(39.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Green Cycling Pants")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724853/krastevs-gym/imgs/shop/shop-women/cycling-pants-green_h7psu4.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Burgundy Cycling Pants")
                        .setDescription("High-waist cycling pants.")
                        .setPrice(BigDecimal.valueOf(39.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Burgundy Cycling Pants")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724852/krastevs-gym/imgs/shop/shop-women/cycling-pants-burgundy_bi4unx.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Brown Cycling Pants")
                        .setDescription("High-waist cycling pants.")
                        .setPrice(BigDecimal.valueOf(39.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Brown Cycling Pants")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724852/krastevs-gym/imgs/shop/shop-women/cycling-pants-brown_jaxpdb.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Black Cycling Pants")
                        .setDescription("High-waist cycling pants.")
                        .setPrice(BigDecimal.valueOf(39.99))
                        .setCategory(WOMEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Black Cycling Pants")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721724851/krastevs-gym/imgs/shop/shop-women/cycling-pants-black_mu7wh5.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("T-shirt with slogan")
                        .setDescription("Short sleeve T-shirt with a round neckline.")
                        .setPrice(BigDecimal.valueOf(25.99))
                        .setCategory(MEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("T-shirt with slogan #1")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728490/krastevs-gym/imgs/shop/shop-men/t-shirt-with-slogan-1_x4lkzd.jpg")
                                        .setAuthor(ADMIN),
                                new PictureEntity()
                                        .setName("T-shirt-with-slogan #2")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728492/krastevs-gym/imgs/shop/shop-men/t-shirt-with-slogan-2_gyrtvb.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("T-shirt with graffiti")
                        .setDescription("Short sleeve T-shirt with a round neckline.")
                        .setPrice(BigDecimal.valueOf(25.99))
                        .setCategory(MEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("T-shirt with graffiti #1")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728485/krastevs-gym/imgs/shop/shop-men/t-shirt-with-graffiti-1_qgwnlp.jpg")
                                        .setAuthor(ADMIN),
                                new PictureEntity()
                                        .setName("T-shirt with graffiti #2")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728486/krastevs-gym/imgs/shop/shop-men/t-shirt-with-graffiti-2_zor5pc.jpg")
                                        .setAuthor(ADMIN),
                                new PictureEntity()
                                        .setName("T-shirt with graffiti #3")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728488/krastevs-gym/imgs/shop/shop-men/t-shirt-with-graffiti-3_gdcqwp.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("T-shirt with Fresh")
                        .setDescription("Short sleeve T-shirt with a round neckline.")
                        .setPrice(BigDecimal.valueOf(25.99))
                        .setCategory(MEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("T-shirt with Fresh #1")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728479/krastevs-gym/imgs/shop/shop-men/t-shirt-with-fresh-1_qup2xw.jpg")
                                        .setAuthor(ADMIN),
                                new PictureEntity()
                                        .setName("T-shirt with Fresh #2")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728481/krastevs-gym/imgs/shop/shop-men/t-shirt-with-fresh-2_atpca4.jpg")
                                        .setAuthor(ADMIN),
                                new PictureEntity()
                                        .setName("T-shirt with Fresh #3")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728483/krastevs-gym/imgs/shop/shop-men/t-shirt-with-fresh-3_rl4h1z.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Fantasy T-shirt")
                        .setDescription("Short sleeve T-shirt with a round neckline.")
                        .setPrice(BigDecimal.valueOf(25.99))
                        .setCategory(MEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Fantasy T-shirt")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728478/krastevs-gym/imgs/shop/shop-men/t-shirt-fantasy_zdtamb.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("White Sports Socks")
                        .setDescription("Elastic knit socks with ribbed elastic trims.")
                        .setPrice(BigDecimal.valueOf(8.99))
                        .setCategory(MEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("White Sports Socks")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728476/krastevs-gym/imgs/shop/shop-men/socks-white_ohlzbx.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("White Sports Sneakers")
                        .setDescription("Trainers with a flat form sole. Lace-up fastening.")
                        .setPrice(BigDecimal.valueOf(199.99))
                        .setCategory(MEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("White Sports Sneakers")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728473/krastevs-gym/imgs/shop/shop-men/sneakers-white_b28caq.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Tie Dye Shorts")
                        .setDescription("Mid-rise shorts with an adjustable inner drawstring.")
                        .setPrice(BigDecimal.valueOf(39.99))
                        .setCategory(MEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Tie Dye Shorts #1")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728470/krastevs-gym/imgs/shop/shop-men/shorts-tie-dye-1_wy8eor.jpg")
                                        .setAuthor(ADMIN),
                                new PictureEntity()
                                        .setName("Tie Dye Shorts #2")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728472/krastevs-gym/imgs/shop/shop-men/shorts-tie-dye-2_ladjfz.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Pink Shorts")
                        .setDescription("Mid-rise shorts with an adjustable inner drawstring.")
                        .setPrice(BigDecimal.valueOf(39.99))
                        .setCategory(MEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Pink Shorts #1")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728467/krastevs-gym/imgs/shop/shop-men/shorts-pink-1_gv4quv.jpg")
                                        .setAuthor(ADMIN),
                                new PictureEntity()
                                        .setName("Pink Shorts #2")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728467/krastevs-gym/imgs/shop/shop-men/shorts-pink-2_hqrpjl.jpg")
                                        .setAuthor(ADMIN),
                                new PictureEntity()
                                        .setName("Pink Shorts #3")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728469/krastevs-gym/imgs/shop/shop-men/shorts-pink-3_jab5rg.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Grey Shorts")
                        .setDescription("Mid-rise shorts with an adjustable inner drawstring.")
                        .setPrice(BigDecimal.valueOf(39.99))
                        .setCategory(MEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Grey Shorts #1")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728461/krastevs-gym/imgs/shop/shop-men/shorts-grey-1_zwoguo.jpg")
                                        .setAuthor(ADMIN),
                                new PictureEntity()
                                        .setName("Grey Shorts #2")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728463/krastevs-gym/imgs/shop/shop-men/shorts-grey-2_yxiujh.jpg")
                                        .setAuthor(ADMIN),
                                new PictureEntity()
                                        .setName("Grey Shorts #3")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728464/krastevs-gym/imgs/shop/shop-men/shorts-grey-3_schxr9.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Burgundy Shorts")
                        .setDescription("Mid-rise shorts with an adjustable inner drawstring.")
                        .setPrice(BigDecimal.valueOf(39.99))
                        .setCategory(MEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Burgundy Shorts #1")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728458/krastevs-gym/imgs/shop/shop-men/shorts-burgundy-1_tkzxog.jpg")
                                        .setAuthor(ADMIN),
                                new PictureEntity()
                                        .setName("Burgundy Shorts #2")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728458/krastevs-gym/imgs/shop/shop-men/shorts-burgundy-2_wwq6am.jpg")
                                        .setAuthor(ADMIN),
                                new PictureEntity()
                                        .setName("Burgundy Shorts #3")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728460/krastevs-gym/imgs/shop/shop-men/shorts-burgundy-3_nuaiwy.jpg")
                                        .setAuthor(ADMIN)
                        )),
                new ProductEntity()
                        .setName("Black Shorts")
                        .setDescription("Mid-rise shorts with an adjustable inner drawstring.")
                        .setPrice(BigDecimal.valueOf(39.99))
                        .setCategory(MEN)
                        .setPictures(List.of(
                                new PictureEntity()
                                        .setName("Black Shorts")
                                        .setUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721728457/krastevs-gym/imgs/shop/shop-men/shorts-black_mb5tkq.jpg")
                                        .setAuthor(ADMIN)
                        )));
    }

    private List<ExerciseEntity> createExerciseEntities() {
        LOGGER.info("===================Creating exercises===================");

        final UserEntity ADMIN = userRepository.findAdminByCategory(UserRoleEnum.ADMIN);

        final ExerciseCategoryEntity ABS = exerciseCategoryRepository.findByCategoryDBInit(ExerciseCategoryEnum.ABS);
        final ExerciseCategoryEntity BICEPS = exerciseCategoryRepository.findByCategoryDBInit(ExerciseCategoryEnum.BICEPS);
        final ExerciseCategoryEntity CHEST = exerciseCategoryRepository.findByCategoryDBInit(ExerciseCategoryEnum.CHEST);
        final ExerciseCategoryEntity FOREARMS = exerciseCategoryRepository.findByCategoryDBInit(ExerciseCategoryEnum.FOREARMS);
        final ExerciseCategoryEntity LATS = exerciseCategoryRepository.findByCategoryDBInit(ExerciseCategoryEnum.LATS);
        final ExerciseCategoryEntity LEGS = exerciseCategoryRepository.findByCategoryDBInit(ExerciseCategoryEnum.LEGS);
        final ExerciseCategoryEntity LOWER_BACK = exerciseCategoryRepository.findByCategoryDBInit(ExerciseCategoryEnum.LOWER_BACK);
        final ExerciseCategoryEntity SHOULDERS = exerciseCategoryRepository.findByCategoryDBInit(ExerciseCategoryEnum.SHOULDERS);
        final ExerciseCategoryEntity TRAPS = exerciseCategoryRepository.findByCategoryDBInit(ExerciseCategoryEnum.TRAPS);
        final ExerciseCategoryEntity TRICEPS = exerciseCategoryRepository.findByCategoryDBInit(ExerciseCategoryEnum.TRICEPS);

        final String MUSCLES_WORKED_URL = "https://res.cloudinary.com/dgtuddxqf/image/upload/v1719702627/krastevs-gym/imgs/muscles/view_of_muscles_kfthdn.jpg";


        return List.of(
                new ExerciseEntity()
                        .setName("Vertical Leg Crunch")
                        .setDescription("""
                                The vertical leg crunch is a bodyweight exercise that targets the rectus abdominis muscle, which is the front layer of your abs.
                                This exercise is similar to the traditional crunch but requires more strength and stability to perform.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701053/krastevs-gym/imgs/abs/gifs/vertical-leg-crunch_wp1su7.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie flat on the floor with the lower back pressed into the ground.
                                2. Place your hands behind your head.
                                3. Bring your knees in towards your chest with your feet together.
                                4. Use your abs to curl your hips off the floor and towards your chest.
                                5. Slowly lower yourself back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(ABS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("V Up")
                        .setDescription("""
                                V ups are a great exercise for your core. They work your rectus abdominis, obliques, and hip flexors.
                                This exercise also helps improve your balance and stability.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701052/krastevs-gym/imgs/abs/gifs/v-up_iilbmq.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie flat on the floor with your legs straight and your arms extended overhead.
                                2. In one movement, lift your legs and torso off the floor, reaching your hands toward your feet.
                                3. Slowly lower yourself back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(ABS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Oblique Crunches")
                        .setDescription("""
                                Oblique crunches are a great way to target your obliques, which are the muscles on the sides of your abdomen.
                                This exercise also works your rectus abdominis, which is the front layer of your abs.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701050/krastevs-gym/imgs/abs/gifs/oblique-crunches-on-the-floor_xyzzyo.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie flat on the floor with your knees bent and your feet flat on the ground.
                                2. Place your hands behind your head.
                                3. Use your abs to curl your torso off the floor and towards your knees.
                                4. Slowly lower yourself back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(ABS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Stability Ball V-Pass")
                        .setDescription("""
                                The stability ball V-pass is a challenging exercise that targets your core muscles, including your rectus abdominis, obliques, and hip flexors.
                                This exercise also helps improve your balance and stability.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701050/krastevs-gym/imgs/abs/gifs/stability-ball-v-pass_e9fle3.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie flat on the floor with your legs straight and your arms extended overhead.
                                2. Hold a stability ball between your feet.
                                3. In one movement, lift your legs and torso off the floor, passing the ball from your feet to your hands.
                                4. Slowly lower yourself back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(ABS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Swiss Ball Rollout")
                        .setDescription("""
                                The Swiss ball rollout is a challenging exercise that targets your core muscles, including your rectus abdominis, obliques, and hip flexors.
                                This exercise also helps improve your balance and stability.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701049/krastevs-gym/imgs/abs/gifs/swiss-ball-rollout_uwivto.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Kneel on the floor with a Swiss ball in front of you.
                                2. Place your forearms on the ball and roll it forward until your body is in a straight line.
                                3. Use your abs to pull the ball back towards you.
                                4. Slowly lower yourself back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(ABS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Stability Ball Jackknife")
                        .setDescription("""
                                The stability ball jackknife is a challenging exercise that targets your core muscles, including your rectus abdominis, obliques, and hip flexors.
                                This exercise also helps improve your balance and stability.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701048/krastevs-gym/imgs/abs/gifs/stability-ball-jackknife_eaahdm.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie flat on the floor with your legs straight and your arms extended overhead.
                                2. Hold a stability ball between your feet.
                                3. In one movement, lift your legs and torso off the floor, bringing the ball towards your hands.
                                4. Slowly lower yourself back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(ABS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Crunches")
                        .setDescription("""
                                Crunches are a classic core exercise that targets your rectus abdominis, which is the front layer of your abs.
                                This exercise is great for building strength and endurance in your core muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701047/krastevs-gym/imgs/abs/gifs/crunch_tykm9l.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie flat on the floor with your knees bent and your feet flat on the ground.
                                2. Place your hands behind your head.
                                3. Use your abs to curl your torso off the floor and towards your knees.
                                4. Slowly lower yourself back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(ABS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Bicycle Crunches")
                        .setDescription("""
                                Bicycle crunches are a great exercise for your core. They work your rectus abdominis, obliques, and hip flexors.
                                This exercise also helps improve your balance and stability.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701046/krastevs-gym/imgs/abs/gifs/bicycle-crunch_dhe7r1.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie flat on the floor with your knees bent and your feet flat on the ground.
                                2. Place your hands behind your head.
                                3. Use your abs to curl your torso off the floor and towards your knees.
                                4. Slowly lower yourself back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(ABS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Sit Up")
                        .setDescription("""
                                Sit-ups are a classic core exercise that targets your rectus abdominis, which is the front layer of your abs.
                                This exercise is great for building strength and endurance in your core muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701045/krastevs-gym/imgs/abs/gifs/sit-up_s5icek.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie flat on the floor with your knees bent and your feet flat on the ground.
                                2. Place your hands behind your head.
                                3. Use your abs to curl your torso off the floor and towards your knees.
                                4. Slowly lower yourself back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(ABS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Twisting Crunch")
                        .setDescription("""
                                Twisting crunches are a great exercise for your core. They work your rectus abdominis, obliques, and hip flexors.
                                This exercise also helps improve your balance and stability.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701045/krastevs-gym/imgs/abs/gifs/twisting-crunch_wublm2.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie flat on the floor with your knees bent and your feet flat on the ground.
                                2. Place your hands behind your head.
                                3. Use your abs to curl your torso off the floor and towards your knees.
                                4. Slowly lower yourself back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(ABS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Kneeling Cable Crunch")
                        .setDescription("""
                                The kneeling cable crunch is a great exercise for your core. It targets your rectus abdominis, obliques, and hip flexors.
                                This exercise also helps improve your balance and stability.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701044/krastevs-gym/imgs/abs/gifs/kneeling-cable-crunch_erzobb.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie flat on the floor with your legs straight and your arms extended overhead.
                                2. In one movement, lift your legs off the floor, keeping them straight.
                                3. Slowly lower yourself back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(ABS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Hanging Leg Raise")
                        .setDescription("""
                                The hanging leg raise is a great exercise for your core. It targets your rectus abdominis, obliques, and hip flexors.
                                This exercise also helps improve your balance and stability.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701043/krastevs-gym/imgs/abs/gifs/hanging-leg-raise_vellze.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie flat on the floor with your legs straight and your arms extended overhead.
                                2. In one movement, lift your legs and torso off the floor, reaching your hands toward your feet.
                                3. Slowly lower yourself back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(ABS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Alternate Lying Floor Leg Raise")
                        .setDescription("""
                                The alternate lying floor leg raise is a great exercise for your core. It targets your rectus abdominis, obliques, and hip flexors.
                                This exercise also helps improve your balance and stability.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701043/krastevs-gym/imgs/abs/gifs/alternate-lying-floor-leg-raise_vtrjos.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie flat on the floor with your legs straight and your arms extended overhead.
                                2. In one movement, lift your legs and torso off the floor, reaching your hands toward your feet.
                                3. Slowly lower yourself back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(ABS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Decline Sit Up")
                        .setDescription("""
                                The decline sit up is a great exercise for your core. It targets your rectus abdominis, obliques, and hip flexors.
                                This exercise also helps improve your balance and stability.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701042/krastevs-gym/imgs/abs/gifs/decline-sit-up_djhkic.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie flat on the floor with your legs straight and your arms extended overhead.
                                2. In one movement, lift your legs and torso off the floor, reaching your hands toward your feet.
                                3. Slowly lower yourself back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(ABS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("One Arm Dumbbell Preacher Curl")
                        .setDescription("""
                                The one arm dumbbell preacher curl is a great exercise for your biceps. It targets your biceps brachii, which is the muscle on the front of your upper arm.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701055/krastevs-gym/imgs/biceps/gifs/one-arm-dumbbell-preacher-curl_qu78vm.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Sit on a preacher bench with a dumbbell in one hand.
                                2. Place your arm on the bench pad with your palm facing up.
                                3. Curl the dumbbell towards your shoulder, keeping your upper arm stationary.
                                4. Slowly lower the dumbbell back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(BICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("EZ Bar Reverse Curl")
                        .setDescription("""
                                The EZ bar reverse curl is a great exercise for your biceps. It targets your biceps brachii, which is the muscle on the front of your upper arm.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701054/krastevs-gym/imgs/biceps/gifs/ez-bar-reverse-curl_vej4c9.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold an EZ bar with an overhand grip.
                                2. Curl the bar towards your shoulders, keeping your upper arms stationary.
                                3. Slowly lower the bar back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(BICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("EZ Bar Curl")
                        .setDescription("""
                                The EZ bar curl is a great exercise for your biceps. It targets your biceps brachii, which is the muscle on the front of your upper arm.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701054/krastevs-gym/imgs/biceps/gifs/ez-bar-curl_wo5uvo.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold an EZ bar with an underhand grip.
                                2. Curl the bar towards your shoulders, keeping your upper arms stationary.
                                3. Slowly lower the bar back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(BICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Dumbbell Reverse Curl")
                        .setDescription("""
                                The dumbbell reverse curl is a great exercise for your biceps. It targets your biceps brachii, which is the muscle on the front of your upper arm.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701054/krastevs-gym/imgs/biceps/gifs/dumbbell-reverse-curl_rh6kj4.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a dumbbell in each hand.
                                2. Curl the dumbbells towards your shoulders, keeping your upper arms stationary.
                                3. Slowly lower the dumbbells back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(BICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Cable Curl")
                        .setDescription("""
                                The cable curl is a great exercise for your biceps. It targets your biceps brachii, which is the muscle on the front of your upper arm.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701053/krastevs-gym/imgs/biceps/gifs/cable-curl_o9n8bk.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a cable attachment in each hand.
                                2. Curl the cable attachment towards your shoulders, keeping your upper arms stationary.
                                3. Slowly lower the cable attachment back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(BICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Dumbbell Hammer Preacher Curl")
                        .setDescription("""
                                The dumbbell hammer preacher curl is a great exercise for your biceps. It targets your biceps brachii, which is the muscle on the front of your upper arm.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701053/krastevs-gym/imgs/biceps/gifs/dumbbell-hammer-preacher-curl_hi8kvd.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Sit on a preacher bench with a dumbbell in each hand.
                                2. Place your arms on the bench pad with your palms facing each other.
                                3. Curl the dumbbells towards your shoulders, keeping your upper arms stationary.
                                4. Slowly lower the dumbbells back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(BICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Dumbbell Curl")
                        .setDescription("""
                                The dumbbell curl is a great exercise for your biceps. It targets your biceps brachii, which is the muscle on the front of your upper arm.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701052/krastevs-gym/imgs/biceps/gifs/dumbbell-curl_hoeed6.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a dumbbell in each hand.
                                2. Curl the dumbbells towards your shoulders, keeping your upper arms stationary.
                                3. Slowly lower the dumbbells back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(BICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Close Grip EZ Bar Curl")
                        .setDescription("""
                                The close grip EZ bar curl is a great exercise for your biceps. It targets your biceps brachii, which is the muscle on the front of your upper arm.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701052/krastevs-gym/imgs/biceps/gifs/close-grip-ez-bar-curl_rgtaly.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold an EZ bar with an underhand grip.
                                2. Curl the bar towards your shoulders, keeping your upper arms stationary.
                                3. Slowly lower the bar back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(BICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Barbell Reverse Curl")
                        .setDescription("""
                                The barbell reverse curl is a great exercise for your biceps. It targets your biceps brachii, which is the muscle on the front of your upper arm.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701051/krastevs-gym/imgs/biceps/gifs/barbell-reverse-curl_wv1vom.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a barbell with an overhand grip.
                                2. Curl the bar towards your shoulders, keeping your upper arms stationary.
                                3. Slowly lower the bar back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(BICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Stability Ball Push Up")
                        .setDescription("""
                                The stability ball push up is a challenging exercise that targets your chest, shoulders, and triceps.
                                This exercise also helps improve your balance and stability.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701057/krastevs-gym/imgs/chest/gifs/stability-ball-push-up_uzim8d.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Place your hands on a stability ball and assume a push-up position.
                                2. Lower your chest towards the ball, keeping your body in a straight line.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(CHEST)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Stability Ball Decline Push Up")
                        .setDescription("""
                                The stability ball decline push up is a challenging exercise that targets your chest, shoulders, and triceps.
                                This exercise also helps improve your balance and stability.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701056/krastevs-gym/imgs/chest/gifs/stability-ball-decline-push-up_x3du4c.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Place your feet on a stability ball and assume a push-up position.
                                2. Lower your chest towards the floor, keeping your body in a straight line.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(CHEST)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("One Leg Push Up")
                        .setDescription("""
                                The one leg push up is a challenging exercise that targets your chest, shoulders, and triceps.
                                This exercise also helps improve your balance and stability.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701054/krastevs-gym/imgs/chest/gifs/one-leg-push-up_zhwrh4.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Assume a push-up position with one leg lifted off the ground.
                                2. Lower your chest towards the floor, keeping your body in a straight line.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(CHEST)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Dumbbell One Arm Reverse Wrist Curl")
                        .setDescription("""
                                The dumbbell one arm reverse wrist curl is a great exercise for your forearms. It targets your wrist extensors, which are the muscles on the back of your forearm.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701059/krastevs-gym/imgs/forearms/gifs/dumbbell-one-arm-reverse-wrist-curl_t2tmqw.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Sit on a bench with your forearm resting on your thigh and your hand hanging over the edge.
                                2. Hold a dumbbell with an overhand grip.
                                3. Curl the dumbbell towards your body, keeping your forearm stationary.
                                4. Slowly lower the dumbbell back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(FOREARMS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Seated Barbell Wrist Curl")
                        .setDescription("""
                                The seated barbell wrist curl is a great exercise for your forearms. It targets your wrist flexors, which are the muscles on the front of your forearm.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701057/krastevs-gym/imgs/forearms/gifs/seated-barbell-wrist-curl_hiyy68.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Sit on a bench with your forearms resting on your thighs and your hands hanging over the edge.
                                2. Hold a barbell with an overhand grip.
                                3. Curl the barbell towards your body, keeping your forearms stationary.
                                4. Slowly lower the barbell back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(FOREARMS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Behind The Back Wrist Curl")
                        .setDescription("""
                                The behind the back wrist curl is a great exercise for your forearms. It targets your wrist flexors, which are the muscles on the front of your forearm.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701055/krastevs-gym/imgs/forearms/gifs/behind-the-back-barbell-wrist-curl_sskw8c.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a barbell behind your back with an overhand grip.
                                2. Curl the barbell towards your body, keeping your forearms stationary.
                                3. Slowly lower the barbell back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(FOREARMS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Bent Over Dumbbell Row")
                        .setDescription("""
                                The bent over dumbbell row is a great exercise for your lats. It targets your latissimus dorsi, which is the large muscle in your back.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701056/krastevs-gym/imgs/lats/gifs/bent-over-dumbbell-row_inbs4j.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a dumbbell in each hand.
                                2. Bend at the waist and lower your torso until it's almost parallel to the floor.
                                3. Pull the dumbbells towards your body, keeping your elbows close to your sides.
                                4. Slowly lower the dumbbells back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(LATS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Prone Incline Wide Grip Upright Row")
                        .setDescription("""
                                The prone incline wide grip upright row is a great exercise for your lats. It targets your latissimus dorsi, which is the large muscle in your back.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701055/krastevs-gym/imgs/lats/gifs/prone-incline-wide-grip-upright-row_qi4mpg.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie face down on an incline bench with a barbell in your hands.
                                2. Pull the barbell towards your body, keeping your elbows close to your sides.
                                3. Slowly lower the barbell back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(LATS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Underhand Yates Row")
                        .setDescription("""
                                The underhand Yates row is a great exercise for your lats. It targets your latissimus dorsi, which is the large muscle in your back.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701040/krastevs-gym/imgs/lats/gifs/underhand-yates-row_g5odde.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a barbell with an underhand grip.
                                2. Pull the barbell towards your body, keeping your elbows close to your sides.
                                3. Slowly lower the barbell back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(LATS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Zercher Squat")
                        .setDescription("""
                                The Zercher squat is a great exercise for your legs. It targets your quadriceps, hamstrings, glutes, and lower back.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701058/krastevs-gym/imgs/legs/gifs/zercher-squat_wmcund.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a barbell in the crook of your elbows.
                                2. Squat down until your thighs are parallel to the ground.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(LEGS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Dumbbell Dead Lift")
                        .setDescription("""
                                The dumbbell dead lift is a great exercise for your legs. It targets your quadriceps, hamstrings, glutes, and lower back.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701058/krastevs-gym/imgs/legs/gifs/dumbbell-deadlift_wjzqdj.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a dumbbell in each hand.
                                2. Bend at the waist and lower the dumbbells towards the ground.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(LEGS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Barbell Side Lunge")
                        .setDescription("""
                                The barbell side lunge is a great exercise for your legs. It targets your quadriceps, hamstrings, glutes, and lower back.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701057/krastevs-gym/imgs/legs/gifs/barbell-side-lunge_ta39ux.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a barbell across your upper back.
                                2. Step to the side with one leg and lower your body into a lunge position.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(LEGS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Barbell Bulgarian Split Squat")
                        .setDescription("""
                                The barbell Bulgarian split squat is a great exercise for your legs. It targets your quadriceps, hamstrings, glutes, and lower back.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701056/krastevs-gym/imgs/legs/gifs/barbell-bulgarian-split-squat_mn5xpb.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a barbell across your upper back.
                                2. Step back with one leg and lower your body into a lunge position.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(LEGS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Dumbbell One Leg Split Squat")
                        .setDescription("""
                                The dumbbell one leg split squat is a great exercise for your legs. It targets your quadriceps, hamstrings, glutes, and lower back.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701049/krastevs-gym/imgs/legs/gifs/dumbbell-one-leg-split-squat_k2mxit.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a dumbbell in each hand.
                                2. Step back with one leg and lower your body into a lunge position.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(LEGS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Barbell Hip Thrust")
                        .setDescription("""
                                The barbell hip thrust is a great exercise for your legs. It targets your quadriceps, hamstrings, glutes, and lower back.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701044/krastevs-gym/imgs/legs/gifs/barbell-hip-thrust_tyscvv.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Sit on the ground with your upper back against a bench and a barbell across your hips.
                                2. Push your hips towards the ceiling, squeezing your glutes at the top.
                                3. Lower your hips back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(LEGS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Trap Bar Dead Lift")
                        .setDescription("""
                                The trap bar dead lift is a great exercise for your lower back. It targets your erector spinae, which are the muscles that run along your spine.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701043/krastevs-gym/imgs/legs/gifs/trap-bar-deadlift_zodsam.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand inside a trap bar with your feet shoulder-width apart.
                                2. Bend at the waist and lower the bar towards the ground.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(LEGS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Cable Hip Abduction")
                        .setDescription("""
                                The cable hip abduction is a great exercise for your lower back. It targets your erector spinae, which are the muscles that run along your spine.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701041/krastevs-gym/imgs/legs/gifs/cable-hip-abduction_bgarhh.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Attach an ankle strap to a cable machine and secure it around your ankle.
                                2. Stand sideways to the machine and lift your leg out to the side.
                                3. Slowly lower your leg back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.CABLE)
                        .setCategory(LEGS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Stability Ball Wall Squat")
                        .setDescription("""
                                The stability ball wall squat is a great exercise for your lower back. It targets your erector spinae, which are the muscles that run along your spine.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701039/krastevs-gym/imgs/legs/gifs/stabilityball-wall-squat_l1oe5k.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with a stability ball between your lower back and a wall.
                                2. Squat down until your thighs are parallel to the ground.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(LEGS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Barbell Lunge")
                        .setDescription("""
                                The barbell lunge is a great exercise for your lower back. It targets your erector spinae, which are the muscles that run along your spine.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701039/krastevs-gym/imgs/legs/gifs/barbell-lunge_cnisdq.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a barbell across your upper back.
                                2. Step forward with one leg and lower your body into a lunge position.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(LEGS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Squat")
                        .setDescription("""
                                The squat is a great exercise for your lower back. It targets your erector spinae, which are the muscles that run along your spine.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701038/krastevs-gym/imgs/legs/gifs/squat_pkenlj.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a barbell across your upper back.
                                2. Squat down until your thighs are parallel to the ground.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(LEGS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Barbell Front Squat")
                        .setDescription("""
                                The barbell front squat is a great exercise for your lower back. It targets your erector spinae, which are the muscles that run along your spine.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701038/krastevs-gym/imgs/legs/gifs/barbell-front-squat_j4diag.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a barbell across your upper chest.
                                2. Squat down until your thighs are parallel to the ground.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(LEGS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Machine Back Extension")
                        .setDescription("""
                                The machine back extension is a great exercise for your lower back. It targets your erector spinae, which are the muscles that run along your spine.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701042/krastevs-gym/imgs/lower-back/gifs/machine-back-extension_iztu7b.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Sit on a back extension machine with your feet secured under the pads.
                                2. Lower your upper body towards the ground.
                                3. Raise your upper body back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.MACHINES)
                        .setCategory(LOWER_BACK)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Seated Dumbbell Overhead Press")
                        .setDescription("""
                                The seated dumbbell overhead press is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701064/krastevs-gym/imgs/shoulders/gifs/seated-dumbbell-overhead-press_ljd3fc.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Sit on a bench with a dumbbell in each hand.
                                2. Press the dumbbells overhead until your arms are fully extended.
                                3. Lower the dumbbells back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Weighted Front Raise")
                        .setDescription("""
                                The weighted front raise is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701062/krastevs-gym/imgs/shoulders/gifs/weighted-front-raise_o4eyvt.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a dumbbell in each hand.
                                2. Raise the dumbbells in front of you until your arms are parallel to the ground.
                                3. Slowly lower the dumbbells back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Lying Dumbbell One Arm Rear Lateral Raise")
                        .setDescription("""
                                The lying dumbbell one arm rear lateral raise is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701062/krastevs-gym/imgs/shoulders/gifs/lying-dumbbell-one-arm-rear-lateral-raise_al4uzm.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie on your side with a dumbbell in your top hand.
                                2. Raise the dumbbell towards the ceiling until your arm is parallel to the ground.
                                3. Slowly lower the dumbbell back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Dumbbell Bent Over Lateral Raise")
                        .setDescription("""
                                The dumbbell bent over lateral raise is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701061/krastevs-gym/imgs/shoulders/gifs/dumbbell-bent-over-lateral-raise_gfjv8c.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a dumbbell in each hand.
                                2. Bend at the waist and lower your torso until it's almost parallel to the ground.
                                3. Raise the dumbbells out to the side until your arms are parallel to the ground.
                                4. Slowly lower the dumbbells back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Seated Alternating Dumbbell Front Raise")
                        .setDescription("""
                                The seated alternating dumbbell front raise is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701061/krastevs-gym/imgs/shoulders/gifs/seated-alternating-dumbbell-front-raise_yuo9vc.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Sit on a bench with a dumbbell in each hand.
                                2. Raise one dumbbell in front of you until your arm is parallel to the ground.
                                3. Lower the dumbbell back down to the starting position.
                                4. Repeat with the other arm.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Dumbbell W Press")
                        .setDescription("""
                                The dumbbell W press is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701060/krastevs-gym/imgs/shoulders/gifs/dumbbell-w-press_nbaydb.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a dumbbell in each hand.
                                2. Raise the dumbbells in front of you until your arms are parallel to the ground.
                                3. Push the dumbbells out to the sides until they form a 'W' shape.
                                4. Slowly lower the dumbbells back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("One Arm Dumbbell Bent Over Lateral Raise")
                        .setDescription("""
                                The one arm dumbbell bent over lateral raise is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701060/krastevs-gym/imgs/shoulders/gifs/one-arm-dumbbell-bent-overlateral-raise_r4jgrs.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a dumbbell in one hand.
                                2. Bend at the waist and lower your torso until it's almost parallel to the ground.
                                3. Raise the dumbbell out to the side until your arm is parallel to the ground.
                                4. Slowly lower the dumbbell back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Kettlebell Kneeling One Arm Bottoms Up Press")
                        .setDescription("""
                                The kettlebell kneeling one arm bottoms up press is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701059/krastevs-gym/imgs/shoulders/gifs/kettlebell-kneeling-one-arm-bottoms-up-press_monpt4.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Kneel on the ground with a kettlebell in one hand.
                                2. Press the kettlebell overhead until your arm is fully extended.
                                3. Lower the kettlebell back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Behind The Neck Barbell Overhead Press")
                        .setDescription("""
                                The behind the neck barbell overhead press is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701059/krastevs-gym/imgs/shoulders/gifs/behind-the-neck-barbell-overhead-press_sir8wa.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a barbell across your upper back.
                                2. Press the barbell overhead until your arms are fully extended.
                                3. Lower the barbell back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Dumbbell Arnold Press")
                        .setDescription("""
                                The dumbbell Arnold press is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701051/krastevs-gym/imgs/shoulders/gifs/dumbbell-arnold-press_g9vavy.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a dumbbell in each hand.
                                2. Press the dumbbells overhead while rotating your palms to face forward.
                                3. Lower the dumbbells back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Cable Standing Rear Delt Horizontal Raw")
                        .setDescription("""
                                The cable standing rear delt horizontal raw is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701042/krastevs-gym/imgs/shoulders/gifs/cable-standing-rear-delt-horizontal-row_wjl3la.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand sideways to a cable machine with the handle in one hand.
                                2. Pull the handle towards your body until your arm is parallel to the ground.
                                3. Slowly lower the handle back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.CABLE)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Barbell Wide Grip Upright Row")
                        .setDescription("""
                                The barbell wide grip upright row is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701040/krastevs-gym/imgs/shoulders/gifs/barbell-wide-grip-upright-row_viosh6.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a barbell with a wide grip.
                                2. Pull the barbell towards your body until your arms are parallel to the ground.
                                3. Slowly lower the barbell back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Cable One Arm Front Raise")
                        .setDescription("""
                                The cable one arm front raise is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701040/krastevs-gym/imgs/shoulders/gifs/cable-one-arm-front-raise_bchyxf.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand sideways to a cable machine with the handle in one hand.
                                2. Raise the handle in front of you until your arm is parallel to the ground.
                                3. Slowly lower the handle back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.CABLE)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Cable Front Raise")
                        .setDescription("""
                                The cable front raise is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701040/krastevs-gym/imgs/shoulders/gifs/cable-front-raise_vtd1ss.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand sideways to a cable machine with the handle in one hand.
                                2. Raise the handle in front of you until your arm is parallel to the ground.
                                3. Slowly lower the handle back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.CABLE)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Alternating Dumbbell Front Raise")
                        .setDescription("""
                                The alternating dumbbell front raise is a great exercise for your shoulders. It targets your deltoids, which are the muscles on the top of your shoulders.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701039/krastevs-gym/imgs/shoulders/gifs/alternating-dumbbell-front-raise_ovaazj.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a dumbbell in each hand.
                                2. Raise one dumbbell in front of you until your arm is parallel to the ground.
                                3. Lower the dumbbell back down to the starting position.
                                4. Repeat with the other arm.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(SHOULDERS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Barbell Shrugs")
                        .setDescription("""
                                The barbell shrugs is a great exercise for your traps. It targets your trapezius, which are the muscles that run along the top of your back.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701041/krastevs-gym/imgs/traps/gifs/barbell-shrug_xtmclb.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a barbell in front of you.
                                2. Shrug your shoulders towards your ears.
                                3. Slowly lower your shoulders back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(TRAPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Dumbbell Shrug")
                        .setDescription("""
                                The dumbbell shrug is a great exercise for your traps. It targets your trapezius, which are the muscles that run along the top of your back.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701038/krastevs-gym/imgs/traps/gifs/dumbbell-shrug_xxicln.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold a dumbbell in each hand.
                                2. Shrug your shoulders towards your ears.
                                3. Slowly lower your shoulders back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(TRAPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Triceps Row Push Down")
                        .setDescription("""
                                The triceps row push down is a great exercise for your triceps. It targets your triceps, which are the muscles on the back of your arms.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701050/krastevs-gym/imgs/triceps/gifs/triceps-rope-push-down_ve4xds.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand in front of a cable machine with a rope attachment.
                                2. Pull the rope down towards your thighs until your arms are fully extended.
                                3. Slowly raise the rope back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.CABLE)
                        .setCategory(TRICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Cable One Arm Reverse Grip Triceps Push Down")
                        .setDescription("""
                                The cable one arm reverse grip triceps push down is a great exercise for your triceps. It targets your triceps, which are the muscles on the back of your arms.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701047/krastevs-gym/imgs/triceps/gifs/cable-one-arm-reverse-grip-triceps-push-down_xcvhup.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand in front of a cable machine with a rope attachment.
                                2. Pull the rope down towards your thighs until your arms are fully extended.
                                3. Slowly raise the rope back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.CABLE)
                        .setCategory(TRICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Reverse Grip Triceps Push Down")
                        .setDescription("""
                                The reverse grip triceps push down is a great exercise for your triceps. It targets your triceps, which are the muscles on the back of your arms.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701047/krastevs-gym/imgs/triceps/gifs/reverse-grip-triceps-pushdown_jyis9l.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand in front of a cable machine with a rope attachment.
                                2. Pull the rope down towards your thighs until your arms are fully extended.
                                3. Slowly raise the rope back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.CABLE)
                        .setCategory(TRICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Bent Knee Bench Dip")
                        .setDescription("""
                                The bent knee bench dip is a great exercise for your triceps. It targets your triceps, which are the muscles on the back of your arms.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701046/krastevs-gym/imgs/triceps/gifs/bent-knee-bench-dip_qiusk5.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Sit on a bench with your hands on the edge.
                                2. Lower your body towards the ground by bending your elbows.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(TRICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Seated Bent Over Two Arm Dumbbell Kickback")
                        .setDescription("""
                                The seated bent over two arm dumbbell kickback is a great exercise for your triceps. It targets your triceps, which are the muscles on the back of your arms.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701046/krastevs-gym/imgs/triceps/gifs/seated-bent-over-two-arm-dumbbell-kickback_tnsvhk.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Sit on a bench with a dumbbell in each hand.
                                2. Bend at the waist and lower your torso until it's almost parallel to the ground.
                                3. Extend your arms behind you until they're fully extended.
                                4. Slowly lower the dumbbells back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(TRICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Barbell Skull Crusher")
                        .setDescription("""
                                The barbell skull crusher is a great exercise for your triceps. It targets your triceps, which are the muscles on the back of your arms.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701044/krastevs-gym/imgs/triceps/gifs/barbell-skull-crusher_a5yh4t.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Lie on a bench with a barbell in your hands.
                                2. Extend your arms towards the ceiling.
                                3. Lower the barbell towards your forehead.
                                4. Push the barbell back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(TRICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Triceps Dip")
                        .setDescription("""
                                The triceps dip is a great exercise for your triceps. It targets your triceps, which are the muscles on the back of your arms.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701041/krastevs-gym/imgs/triceps/gifs/triceps-dip_set7r5.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Sit on a bench with your hands on the edge.
                                2. Lower your body towards the ground by bending your elbows.
                                3. Push yourself back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BODYWEIGHT)
                        .setCategory(TRICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Seated Dumbbell Overhead Triceps Extension")
                        .setDescription("""
                                The seated dumbbell overhead triceps extension is a great exercise for your triceps. It targets your triceps, which are the muscles on the back of your arms.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701039/krastevs-gym/imgs/triceps/gifs/seated-dumbbell-overhead-triceps-extension_snlehu.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Sit on a bench with a dumbbell in each hand.
                                2. Extend your arms towards the ceiling.
                                3. Lower the dumbbells behind your head.
                                4. Push the dumbbells back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.DUMBBELLS)
                        .setCategory(TRICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Overhead EZ Bar Triceps Extension")
                        .setDescription("""
                                The overhead EZ bar triceps extension is a great exercise for your triceps. It targets your triceps, which are the muscles on the back of your arms.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701038/krastevs-gym/imgs/triceps/gifs/overhead-ez-bar-triceps-extension_mtljrx.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand with your feet shoulder-width apart and hold an EZ bar overhead.
                                2. Lower the EZ bar behind your head.
                                3. Push the EZ bar back up to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.BARBELL)
                        .setCategory(TRICEPS)
                        .setUser(ADMIN),
                new ExerciseEntity()
                        .setName("Cable Triceps Kickback")
                        .setDescription("""
                                The cable triceps kickback is a great exercise for your triceps. It targets your triceps, which are the muscles on the back of your arms.
                                This exercise also helps improve your grip strength and forearm muscles.""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701038/krastevs-gym/imgs/triceps/gifs/cable-triceps-kickback_hcojim.gif")
                        .setMusclesWorkedUrl(MUSCLES_WORKED_URL)
                        .setInstructions("""
                                1. Stand in front of a cable machine with a handle in one hand.
                                2. Extend your arm behind you until it's fully extended.
                                3. Slowly lower your arm back down to the starting position.""")
                        .setEquipmentType(EquipmentTypeEnum.CABLE)
                        .setCategory(TRICEPS)
                        .setUser(ADMIN)
        );
    }

    private static List<ExerciseCategoryEntity> createExerciseCategoryEntities() {
        LOGGER.info("===================Creating exercise categories===================");

        return List.of(
                new ExerciseCategoryEntity()
                        .setCategory(ExerciseCategoryEnum.ABS)
                        .setDescription("""
                                Abdominal muscles play a crucial role in posture, support of the spine, balance, stability, and respiratory functions such as breathing.
                                If you've been skipping your ab session, let's start crushing your core!""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701051/krastevs-gym/imgs/exercises/gifs/abs_br2dbt.gif"),
                new ExerciseCategoryEntity()
                        .setCategory(ExerciseCategoryEnum.BICEPS)
                        .setDescription("""
                                Strong arm muscles are good for more than carrying your girlfriend's end-of-season-sale shopping haul.
                                If you've been skipping your arms day, let's start strengthening your guns!""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701049/krastevs-gym/imgs/exercises/gifs/biceps_bg9z4r.gif"),
                new ExerciseCategoryEntity()
                        .setCategory(ExerciseCategoryEnum.CHEST)
                        .setDescription("""
                                Chest exercises are an important way to build upper body strength.
                                Strengthening the muscles making up the chest can help you achieve a strong, sculpted physique.
                                Let's start training for a stronger chest!""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701060/krastevs-gym/imgs/exercises/gifs/chest_olpma2.gif"),
                new ExerciseCategoryEntity()
                        .setCategory(ExerciseCategoryEnum.FOREARMS)
                        .setDescription("""
                                Strong arm muscles are good for more than carrying your girlfriend's end-of-season-sale shopping haul.
                                If you've been skipping your arms day, let's start strengthening your guns!""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701061/krastevs-gym/imgs/exercises/gifs/forearms_sekwzg.gif"),
                new ExerciseCategoryEntity()
                        .setCategory(ExerciseCategoryEnum.LATS)
                        .setDescription("""
                                It takes a strong back to lead a strong life!
                                In fact, a weak back is one of the most common causes of back pain and injury.
                                Is your back strong enough?""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701046/krastevs-gym/imgs/exercises/gifs/back_ttxpl3.gif"),
                new ExerciseCategoryEntity()
                        .setCategory(ExerciseCategoryEnum.LEGS)
                        .setDescription("""
                                Leg exercises can be tough.
                                Of all the workout days, they can get the most groans.
                                Don't skip leg day, prioritise it!""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701051/krastevs-gym/imgs/exercises/gifs/legs_vv3pe0.gif"),
                new ExerciseCategoryEntity()
                        .setCategory(ExerciseCategoryEnum.LOWER_BACK)
                        .setDescription("""
                                It takes a strong back to lead a strong life!
                                In fact, a weak back is one of the most common causes of back pain and injury.
                                Is your back strong enough?""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721175473/krastevs-gym/imgs/exercises/gifs/lower-back_ozahle.gif"),
                new ExerciseCategoryEntity()
                        .setCategory(ExerciseCategoryEnum.SHOULDERS)
                        .setDescription("""
                                The shoulders are a complex and vital joint system.
                                Strong shoulders significantly improve upper-body strength and stability.
                                Are you ready to slay those shoulders?""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701048/krastevs-gym/imgs/exercises/gifs/shoulders_pcctie.gif"),
                new ExerciseCategoryEntity()
                        .setCategory(ExerciseCategoryEnum.TRAPS)
                        .setDescription("""
                                It takes a strong back to lead a strong life!
                                In fact, a weak back is one of the most common causes of back pain and injury.
                                Is your back strong enough?""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1721175591/krastevs-gym/imgs/exercises/gifs/traps_dvlc1m.gif"),
                new ExerciseCategoryEntity()
                        .setCategory(ExerciseCategoryEnum.TRICEPS)
                        .setDescription("""
                                Strong arm muscles are good for more than carrying your girlfriend's end-of-season-sale shopping haul.
                                If you've been skipping your arms day, let's start strengthening your guns!""")
                        .setGifUrl("https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701058/krastevs-gym/imgs/exercises/gifs/triceps_uf74jh.gif")
        );
    }

    private void initUser(List<UserRoleEntity> roles) {
        LOGGER.info("===================Creating user roles===================");

        UserEntity chewbacca = new UserEntity()
                .setFirstName("Chewbacca")
                .setLastName("Krastev-Waller")
                .setAge(9)
                .setWeight(5)
                .setHeight(30)
                .setEmail("chewbacca@web.de")
                .setPassword(passwordEncoder.encode("chewbacca"))
                .setRoles(new HashSet<>(roles));

        userRepository.save(chewbacca);

        UserEntity neli = new UserEntity()
                .setFirstName("Neli")
                .setLastName("Raykova")
                .setAge(68)
                .setWeight(56)
                .setHeight(168)
                .setEmail("neliraykova@web.de")
                .setPassword(passwordEncoder.encode("neliraykova"))
                .setRoles(new HashSet<>(roles));

        userRepository.save(neli);

        UserEntity gabriele = new UserEntity()
                .setFirstName("Gabriele")
                .setLastName("Waller")
                .setAge(60)
                .setWeight(56)
                .setHeight(168)
                .setEmail("gabrielewaller@web.de")
                .setPassword(passwordEncoder.encode("gabrielewaller"))
                .setRoles(new HashSet<>(roles));

        userRepository.save(gabriele);

        UserEntity jeffrey = new UserEntity()
                .setFirstName("Jeffrey")
                .setLastName("Waller")
                .setAge(55)
                .setWeight(82)
                .setHeight(180)
                .setEmail("jeffreywaller@web.de")
                .setPassword(passwordEncoder.encode("jeffreywaller"))
                .setRoles(new HashSet<>(roles));

        userRepository.save(jeffrey);

        UserEntity darius = new UserEntity()
                .setFirstName("Darius")
                .setLastName("Waller")
                .setAge(27)
                .setWeight(80)
                .setHeight(180)
                .setEmail("dariuswaller@web.de")
                .setPassword(passwordEncoder.encode("dariuswaller"))
                .setRoles(new HashSet<>(roles));

        userRepository.save(darius);

    }

    private void initModerator(List<UserRoleEntity> roles) {
        LOGGER.info("===================Creating a moderator role===================");
        UserEntity moderator = new UserEntity()
                .setFirstName("Dominique-Shanecé")
                .setLastName("Waller")
                .setAge(32)
                .setWeight(72)
                .setHeight(178)
                .setEmail("shanece@web.de")
                .setPassword(passwordEncoder.encode("shanece"))
                .setRoles(new HashSet<>(roles));

        userRepository.save(moderator);
    }

    private void initAdmin(List<UserRoleEntity> roles) {
        LOGGER.info("===================Creating an admin role===================");
        LOGGER.info("===================email: todorkrastev@web.de===================");
        LOGGER.info("===================password: V170sh@229O===================");
        LOGGER.info("===================Please change the Admin password immediately.===================");


        UserEntity admin = new UserEntity()
                .setFirstName("Todor")
                .setLastName("Krastev")
                .setAge(34)
                .setWeight(92)
                .setHeight(188)
                .setEmail("todorkrastev@web.de")
                .setPassword(passwordEncoder.encode(adminPass))
                .setRoles(new HashSet<>(roles));

        userRepository.save(admin);
    }
}
