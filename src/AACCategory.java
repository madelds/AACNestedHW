import structures.AssociativeArray;
import structures.KeyNotFoundException;

/**
 * The AACCategory class is a part of the AAC system.
 * It stores the mapping between image locations and 
 * the text that should be spoken, as well as the name 
 * of the category.
 *
 * @author Madel Sibal
 * @author Samuel A. Rebelsky
 */

public class AACCategory {

  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * Holds the category name.
   */
  public final String categoryName = null;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The name of the category.
   */
  private String name;

  /**
   * Associative array that maps image locations to text.
   */
  AssociativeArray<String, String> imageTextMap; // Map image locations to text

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Initializes an AACCategory with a given category name.
   */
  public AACCategory(String categoryName) {
      name = categoryName;
      imageTextMap = new AssociativeArray<>();
  }

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Adds a mapping of the image location to the text in the category.
   */
  public void addItem(String imageLoc, String text) {
      imageTextMap.set(imageLoc, text);
  }

  /**
   * Gets the name of the category.
   */
  public String getCategory() {
      return name;
  }

  /**
   * Gets an array of all image locations in the category.
   */
  public String[] getImages() {
      Object[] objectImageLocs = imageTextMap.getKeys();
      String[] imageLocs = new String[objectImageLocs.length];

      for (int i = 0; i < objectImageLocs.length; i++) {
          imageLocs[i] = objectImageLocs[i].toString();
      }

      return imageLocs;
  }

  /**
   * Gets the text associated with a given image location.
   */
  public String getText(String imageLoc) {
      try {
          return imageTextMap.get(imageLoc);
      } catch (KeyNotFoundException e) {
          // Handles the case when the image location is not found.
          return "Image not found";
      }
  }

  /**
   * Checks if a given image location is stored in the category.
   */
  public boolean hasImage(String imageLoc) {
      return imageTextMap.hasKey(imageLoc);
  }

  /**
   * Gets the current category.
   */
  public AssociativeArray<String, AACCategory> getCurrentCategory() {
      return null;
  }
}