SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Person` (
  `firstName` VARCHAR(45) NOT NULL,
  `userName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userName`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Chef`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Chef` (
  `userName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userName`),
  CONSTRAINT `fk_Chef_1`
    FOREIGN KEY (`userName`)
    REFERENCES `mydb`.`Person` (`userName`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `userName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userName`),
  CONSTRAINT `fk_User_1`
    FOREIGN KEY (`userName`)
    REFERENCES `mydb`.`Person` (`userName`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Payment` (
  `userName` VARCHAR(45) NOT NULL,
  `address` VARCHAR(85) NOT NULL,
  `creditCard` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`userName`),
  CONSTRAINT `userName`
    FOREIGN KEY (`userName`)
    REFERENCES `mydb`.`User` (`userName`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Recipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Recipe` (
  `recipeId` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL,
  `picture` BLOB NULL,
  `parientId` INT NULL,
  `childId` INT NULL,
  `steps` VARCHAR(45) NULL,
  `cuisine` VARCHAR(45) NULL,
  PRIMARY KEY (`recipeId`),
    FOREIGN KEY (`parientId`)
    REFERENCES `mydb`.`Recipe` (`recipeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`cuisine`)
    REFERENCES `mydb`.`Cuisines` (`cuisineName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`childId`)
    REFERENCES `mydb`.`Recipe` (`recipeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
	)
	
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`WeeklyRecipes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`WeeklyRecipes` (
  `weeklyRecipeId` INT NOT NULL AUTO_INCREMENT,
  `recipeId` INT NULL,
  `week` DATE NULL,
  PRIMARY KEY (`weeklyRecipeId`),
  CONSTRAINT `fk_WeeklyRecipes_1`
    FOREIGN KEY (`recipeId`)
    REFERENCES `mydb`.`Recipe` (`recipeId`)
    ON UPDATE CASCADE
    ON DELETE SET NULL
	)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Order` (
  `weeklyRecipeId` INT NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `deliveryTime` DATETIME NULL,
  `quantity` INT NULL,
  `rating` INT NULL,
  `confirmation` TINYINT(1) NULL,
  `accepted` TINYINT(1) NULL,
  `comment` VARCHAR(255) NULL,
  `orderComplete` TINYINT(1) NULL,
  PRIMARY KEY (`weeklyRecipeId`, `userName`),
  INDEX `fk_Order_1_idx` (`userName` ASC),
  CONSTRAINT `fk_Order_1`
    FOREIGN KEY (`userName`)
    REFERENCES `mydb`.`User` (`userName`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Order_2`
    FOREIGN KEY (`weeklyRecipeId`)
    REFERENCES `mydb`.`WeeklyRecipes` (`weeklyRecipeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`type` (
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`type`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Ingredient` (
  `ingredientName` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ingredientName`),
  CONSTRAINT `fk_Ingredient_1`
    FOREIGN KEY (`type`)
    REFERENCES `mydb`.`type` (`type`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`RecipeIngredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`RecipeIngredient` (
  `recipeId` INT NOT NULL,
  `ingredientName` VARCHAR(45) NOT NULL,
  `quantity` INT NULL,
  PRIMARY KEY (`recipeId`, `ingredientName`),
  CONSTRAINT `fk_RecipeIngredient_1`
    FOREIGN KEY (`recipeId`)
    REFERENCES `mydb`.`Recipe` (`recipeId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_RecipeIngredient_2`
    FOREIGN KEY (`ingredientName`)
    REFERENCES `mydb`.`Ingredient` (`ingredientName`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Cuisines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cuisines` (
  `cuisineName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cuisineName`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Preference`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Preference` (
  `userName` VARCHAR(45) NOT NULL,
  `cuisineName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cuisineName`, `userName`),
  CONSTRAINT `fk_Preference_1`
    FOREIGN KEY (`userName`)
    REFERENCES `mydb`.`User` (`userName`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Preference_2`
    FOREIGN KEY (`cuisineName`)
    REFERENCES `mydb`.`Cuisines` (`cuisineName`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Restriction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Restriction` (
  `restriction` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`restriction`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`RestrictedType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`RestrictedType` (
  `restriction` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`restriction`, `type`),
  CONSTRAINT `fk_RestrictedType_1`
    FOREIGN KEY (`restriction`)
    REFERENCES `mydb`.`Restriction` (`restriction`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_RestrictedType_2`
    FOREIGN KEY (`type`)
    REFERENCES `mydb`.`type` (`type`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`Diet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Diet` (
  `userName` VARCHAR(45) NOT NULL,
  `restriction` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`userName`, `restriction`),
  CONSTRAINT `fk_Diet_1`
    FOREIGN KEY (`userName`)
    REFERENCES `mydb`.`User` (`userName`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Diet_2`
    FOREIGN KEY (`restriction`)
    REFERENCES `mydb`.`Restriction` (`restriction`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



