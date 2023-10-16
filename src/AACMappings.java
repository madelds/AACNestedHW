import structures.AssociativeArray;
import structures.KeyNotFoundException;
import java.io.*;

/**
 * The AACMappings class is a part of the AAC system.
 * It store the mapping of the images on the home page 
 * to the AACCategories.
 * 
 * @author Madel Sibal
 */

public class AACMappings {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * Holds mappings for all pages in the AAC system.
   */
  AACCategory currentCategory;

  /**
   * Maps strings to AACCategory objects.
   */
  AssociativeArray<String, AACCategory> nameToCategoryMap;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructs mappings for the AAC system.
   */
  public AACMappings(String filename) {
    currentCategory = new AACCategory("");
    nameToCategoryMap = new AssociativeArray<>();
    nameToCategoryMap.set("", currentCategory);
  }
  
  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Adds a mapping of an image location to text in the 
   * current category or the default category.
   */
  public void add(String imageLoc, String text) {
    currentCategory.addItem(imageLoc, text);
    if (isCategory(imageLoc)) {
        nameToCategoryMap.set(imageLoc, new AACCategory(text));
    }
  }

  /**
   * Retrieves an array of all the image locations in the current category.
   */
  public String[] getImageLocs() {
    return currentCategory.getImages();
}

  /**
   * Given an image location, retrieves the associated text with the image 
   * and updates the current category if the image is a category.
   */
  public String getText(String imageLoc) {
    String text = currentCategory.getText(imageLoc);
    if (isCategory(imageLoc)) {
        setCategory(imageLoc);
    }
    return text;
}

  /**
   * Retrieves the name of the current category.
   */
  public String getCurrentCategory() {
    return currentCategory.getCategory();
}

  /**
   * Checks if the given image location represents a category or text to be spoken.
   */
  public boolean isCategory(String imageLoc) {
    String[] categoryImagePaths = {
        "img/food/plate.png",
        "img/clothing/hanger.png"
    };

    for (String categoryPath : categoryImagePaths) {
        if (imageLoc.equals(categoryPath)) {
            return true;
        }
    }

    return false;
}

  /**
   * Resets the current category of the AAC system back to the default category.
   */
  public void reset() {
    setCategory("");
}

  /**
   * Writes the AAC mappings to a file with structured content.
   */
public void writeToFile(String filename) {
  try (FileWriter fileWriter = new FileWriter(filename);
       PrintWriter writer = new PrintWriter(fileWriter)) {

      for (int i = 1; i < nameToCategoryMap.size(); i++) {
          String categoryKey = nameToCategoryMap.retrieveKey(i);
          AACCategory category = nameToCategoryMap.get(categoryKey);

          writer.println(categoryKey + " " + category.getText(categoryKey));

          String[] imagesInCategory = category.getImages();

          for (String imageKey : imagesInCategory) {
              writer.println(">" + imageKey + " " + category.getText(imageKey));
          }
      }
  } catch (IOException | KeyNotFoundException e) {
      System.err.println("An error occurred while exporting the AAC mappings to the file: " + e.getMessage());
      System.exit(1);
  }
}

  /**
   * Sets the current category to the one represented by the given key.
   */
  private void setCategory(String key) {
    try {
        currentCategory = nameToCategoryMap.get(key);
    } catch (Exception e) {
    }
  }
}
