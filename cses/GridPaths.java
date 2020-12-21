import java.util.Scanner;

class GridPaths {
  public static final int BOARD_SIZE = 7;
  public static int[][] BOARD = new int[GridPaths.BOARD_SIZE + 2][GridPaths.BOARD_SIZE + 2];
  public static int gridPaths(String originalString) {
    for (int i = 0; i < GridPaths.BOARD.length; i++) {
      GridPaths.BOARD[0][i] = 1;
      GridPaths.BOARD[GridPaths.BOARD_SIZE + 1][i] = 1;
      GridPaths.BOARD[i][0] = 1;
      GridPaths.BOARD[i][GridPaths.BOARD_SIZE + 1] = 1;
    }
    GridPaths.BOARD[1][1] = 1;
    int firstExactIndex = -1;
    int lastExactIndex = -1;
    for (int i = 0; i < originalString.length(); i++) {
      if (originalString.charAt(i) != '?' && firstExactIndex == -1) {
        firstExactIndex = i;
        break;
      }
    }
    for (int i = originalString.length() - 1; i >= 0; i--) {
      if (originalString.charAt(i) != '?' && lastExactIndex == -1) {
        lastExactIndex = i;
        break;
      }
    }
    if (firstExactIndex > originalString.length() - lastExactIndex) {
      originalString = GridPaths.revertString(originalString);
    }
    return GridPaths.checkPath(originalString, 0, 1, 1);
  }

  public static String revertString(String originalString) {
    String s = "";
    for (int i = 0; i < originalString.length(); i++) {
      switch (originalString.charAt(i)) {
        case 'L':
          s = 'R' + s;
          break;
        case 'R':
          s = 'L' + s;
          break;
        default:
          s = originalString.charAt(i) + s;
      }
    }
    return s;
  }

  public static int checkPath(String originalString, int currentStrIndex, int prevI, int prevJ) {
    if (currentStrIndex == originalString.length()) {
      if (prevI == GridPaths.BOARD_SIZE && prevJ == 1) {
        return 1;
      }
      return 0;
    }
    int pathsNumber = 0;

    if (GridPaths.BOARD[prevI - 1][prevJ] == 0) {
      if (GridPaths.BOARD[prevI - 2][prevJ] == 0 && GridPaths.BOARD[prevI - 1][prevJ - 1] == 1 && GridPaths.BOARD[prevI - 1][prevJ + 1] == 1) {
        if ((originalString.charAt(currentStrIndex) == 'U' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'U' || originalString.charAt(currentStrIndex + 1) == '?')) {
          GridPaths.BOARD[prevI - 1][prevJ] = 1;
          GridPaths.BOARD[prevI - 2][prevJ] = 1;
          pathsNumber += GridPaths.checkPath(originalString, currentStrIndex + 2, prevI - 2, prevJ);
          GridPaths.BOARD[prevI - 1][prevJ] = 0;
          GridPaths.BOARD[prevI - 2][prevJ] = 0;
          return pathsNumber;
        } else {
          return 0;
        }
      } else if (GridPaths.BOARD[prevI - 2][prevJ] == 1 && GridPaths.BOARD[prevI - 1][prevJ - 1] == 0 && GridPaths.BOARD[prevI - 1][prevJ + 1] == 1) {
        if ((originalString.charAt(currentStrIndex) == 'U' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'L' || originalString.charAt(currentStrIndex + 1) == '?')) {
          GridPaths.BOARD[prevI - 1][prevJ] = 1;
          GridPaths.BOARD[prevI - 1][prevJ - 1] = 1;
          pathsNumber += GridPaths.checkPath(originalString, currentStrIndex + 2, prevI - 1, prevJ - 1);
          GridPaths.BOARD[prevI - 1][prevJ] = 0;
          GridPaths.BOARD[prevI - 1][prevJ - 1] = 0;
          return pathsNumber;
        } else {
          return 0;
        }        
      } else if (GridPaths.BOARD[prevI - 2][prevJ] == 1 && GridPaths.BOARD[prevI - 1][prevJ - 1] == 1 && GridPaths.BOARD[prevI - 1][prevJ + 1] == 0) {
        if ((originalString.charAt(currentStrIndex) == 'U' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'R' || originalString.charAt(currentStrIndex + 1) == '?')) {
          GridPaths.BOARD[prevI - 1][prevJ] = 1;
          GridPaths.BOARD[prevI - 1][prevJ + 1] = 1;
          pathsNumber += GridPaths.checkPath(originalString, currentStrIndex + 2, prevI - 1, prevJ + 1);
          GridPaths.BOARD[prevI - 1][prevJ] = 0;
          GridPaths.BOARD[prevI - 1][prevJ + 1] = 0;
          return pathsNumber;
        } else {
          return 0;
        }
      }
    }

    if (GridPaths.BOARD[prevI + 1][prevJ] == 0) {
      if (GridPaths.BOARD[prevI + 2][prevJ] == 0 && GridPaths.BOARD[prevI + 1][prevJ - 1] == 1 && GridPaths.BOARD[prevI + 1][prevJ + 1] == 1) {
        if ((originalString.charAt(currentStrIndex) == 'D' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'D' || originalString.charAt(currentStrIndex + 1) == '?')) {
          GridPaths.BOARD[prevI + 1][prevJ] = 1;
          GridPaths.BOARD[prevI + 2][prevJ] = 1;
          pathsNumber += GridPaths.checkPath(originalString, currentStrIndex + 2, prevI + 2, prevJ);
          GridPaths.BOARD[prevI + 1][prevJ] = 0;
          GridPaths.BOARD[prevI + 2][prevJ] = 0;
          return pathsNumber;
        } else {
          return 0;
        }
      } else if (GridPaths.BOARD[prevI + 2][prevJ] == 1 && GridPaths.BOARD[prevI + 1][prevJ - 1] == 0 && GridPaths.BOARD[prevI + 1][prevJ + 1] == 1) {
        if ((originalString.charAt(currentStrIndex) == 'D' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'L' || originalString.charAt(currentStrIndex + 1) == '?')) {
          GridPaths.BOARD[prevI + 1][prevJ] = 1;
          GridPaths.BOARD[prevI + 1][prevJ - 1] = 1;
          pathsNumber += GridPaths.checkPath(originalString, currentStrIndex + 2, prevI + 1, prevJ - 1);
          GridPaths.BOARD[prevI + 1][prevJ] = 0;
          GridPaths.BOARD[prevI + 1][prevJ - 1] = 0;
          return pathsNumber;
        } else {
          return 0;
        }        
      } else if (GridPaths.BOARD[prevI + 2][prevJ] == 1 && GridPaths.BOARD[prevI + 1][prevJ - 1] == 1 && GridPaths.BOARD[prevI + 1][prevJ + 1] == 0) {
        if (prevI + 1 == GridPaths.BOARD_SIZE && prevJ == 1) {
          // It's a step to exit cell.
        } else {
          if ((originalString.charAt(currentStrIndex) == 'D' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'R' || originalString.charAt(currentStrIndex + 1) == '?')) {
            GridPaths.BOARD[prevI + 1][prevJ] = 1;
            GridPaths.BOARD[prevI + 1][prevJ + 1] = 1;
            pathsNumber += GridPaths.checkPath(originalString, currentStrIndex + 2, prevI + 1, prevJ + 1);
            GridPaths.BOARD[prevI + 1][prevJ] = 0;
            GridPaths.BOARD[prevI + 1][prevJ + 1] = 0;
            return pathsNumber;
          } else {
            return 0;
          }
        }
      }
    }

    if (GridPaths.BOARD[prevI][prevJ - 1] == 0) {
      if (GridPaths.BOARD[prevI][prevJ - 2] == 0 && GridPaths.BOARD[prevI - 1][prevJ - 1] == 1 && GridPaths.BOARD[prevI + 1][prevJ - 1] == 1) {
        if ((originalString.charAt(currentStrIndex) == 'L' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'L' || originalString.charAt(currentStrIndex + 1) == '?')) {
          GridPaths.BOARD[prevI][prevJ - 1] = 1;
          GridPaths.BOARD[prevI][prevJ - 2] = 1;
          pathsNumber += GridPaths.checkPath(originalString, currentStrIndex + 2, prevI, prevJ - 2);
          GridPaths.BOARD[prevI][prevJ - 1] = 0;
          GridPaths.BOARD[prevI][prevJ - 2] = 0;
          return pathsNumber;
        } else {
          return 0;
        }
     } else if (GridPaths.BOARD[prevI][prevJ - 2] == 1 && GridPaths.BOARD[prevI - 1][prevJ - 1] == 0 && GridPaths.BOARD[prevI + 1][prevJ - 1] == 1) {
       if (prevI == GridPaths.BOARD_SIZE && prevJ == 2) {
         // It's step to exit cell.
       } else {
         if ((originalString.charAt(currentStrIndex) == 'L' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'U' || originalString.charAt(currentStrIndex + 1) == '?')) {
           GridPaths.BOARD[prevI][prevJ - 1] = 1;
           GridPaths.BOARD[prevI - 1][prevJ - 1] = 1;
           pathsNumber += GridPaths.checkPath(originalString, currentStrIndex + 2, prevI - 1, prevJ - 1);
           GridPaths.BOARD[prevI][prevJ - 1] = 0;
           GridPaths.BOARD[prevI - 1][prevJ - 1] = 0;
           return pathsNumber;
         } else {
           return 0;
         }
       }        
      } else if (GridPaths.BOARD[prevI][prevJ - 2] == 1 && GridPaths.BOARD[prevI - 1][prevJ - 1] == 1 && GridPaths.BOARD[prevI + 1][prevJ - 1] == 0) {
        if ((originalString.charAt(currentStrIndex) == 'L' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'D' || originalString.charAt(currentStrIndex + 1) == '?')) {
          GridPaths.BOARD[prevI][prevJ - 1] = 1;
          GridPaths.BOARD[prevI + 1][prevJ - 1] = 1;
          pathsNumber += GridPaths.checkPath(originalString, currentStrIndex + 2, prevI + 1, prevJ - 1);
          GridPaths.BOARD[prevI][prevJ - 1] = 0;
          GridPaths.BOARD[prevI + 1][prevJ - 1] = 0;
          return pathsNumber;
        } else {
          return 0;
        }
      }
    }

    if (GridPaths.BOARD[prevI][prevJ + 1] == 0) {
      if (GridPaths.BOARD[prevI][prevJ + 2] == 0 && GridPaths.BOARD[prevI - 1][prevJ + 1] == 1 && GridPaths.BOARD[prevI + 1][prevJ + 1] == 1) {
        if ((originalString.charAt(currentStrIndex) == 'R' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'R' || originalString.charAt(currentStrIndex + 1) == '?')) {
          GridPaths.BOARD[prevI][prevJ + 1] = 1;
          GridPaths.BOARD[prevI][prevJ + 2] = 1;
          pathsNumber += GridPaths.checkPath(originalString, currentStrIndex + 2, prevI, prevJ + 2);
          GridPaths.BOARD[prevI][prevJ + 1] = 0;
          GridPaths.BOARD[prevI][prevJ + 2] = 0;
          return pathsNumber;
        } else {
          return 0;
        }
      } else if (GridPaths.BOARD[prevI][prevJ + 2] == 1 && GridPaths.BOARD[prevI - 1][prevJ + 1] == 0 && GridPaths.BOARD[prevI + 1][prevJ + 1] == 1) {
        if ((originalString.charAt(currentStrIndex) == 'R' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'U' || originalString.charAt(currentStrIndex + 1) == '?')) {
          GridPaths.BOARD[prevI][prevJ + 1] = 1;
          GridPaths.BOARD[prevI - 1][prevJ + 1] = 1;
          pathsNumber += GridPaths.checkPath(originalString, currentStrIndex + 2, prevI - 1, prevJ + 1);
          GridPaths.BOARD[prevI][prevJ + 1] = 0;
          GridPaths.BOARD[prevI - 1][prevJ + 1] = 0;
          return pathsNumber;
        } else {
          return 0;
        }        
      } else if (GridPaths.BOARD[prevI][prevJ + 2] == 1 && GridPaths.BOARD[prevI - 1][prevJ + 1] == 1 && GridPaths.BOARD[prevI + 1][prevJ + 1] == 0) {
        if ((originalString.charAt(currentStrIndex) == 'R' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'D' || originalString.charAt(currentStrIndex + 1) == '?')) {
          GridPaths.BOARD[prevI][prevJ + 1] = 1;
          GridPaths.BOARD[prevI + 1][prevJ + 1] = 1;
          pathsNumber += GridPaths.checkPath(originalString, currentStrIndex + 2, prevI + 1, prevJ + 1);
          GridPaths.BOARD[prevI][prevJ + 1] = 0;
          GridPaths.BOARD[prevI + 1][prevJ + 1] = 0;
          return pathsNumber;
        } else {
          return 0;
        }
      }
    }

    if (prevI == GridPaths.BOARD_SIZE - 1 && prevJ == 1) {
      if (currentStrIndex < originalString.length() - 1) {
        if (originalString.charAt(currentStrIndex) == 'D') {
          return 0;
        }
      } else {
        if (originalString.charAt(currentStrIndex) != 'D' && originalString.charAt(currentStrIndex) != '?') {
          return 0;
        } else {
          return 1;
        }
      }
    }

    if (prevI == GridPaths.BOARD_SIZE && prevJ == 2) {
      if (currentStrIndex < originalString.length() - 1) {
        if (originalString.charAt(currentStrIndex) == 'L') {
          return 0;
        }
      } else {
        if (originalString.charAt(currentStrIndex) != 'L' && originalString.charAt(currentStrIndex) != '?') {
          return 0;
        } else {
          return 1;
        }
      }
    }

    if (prevI == GridPaths.BOARD_SIZE && prevJ == 1 && currentStrIndex < originalString.length() - 1) {
      return 0;
    }

    if (GridPaths.BOARD[prevI - 1][prevJ] == 0 && GridPaths.BOARD[prevI + 1][prevJ] == 0 && GridPaths.BOARD[prevI][prevJ - 1] == 1 && GridPaths.BOARD[prevI][prevJ + 1] == 1) {
      return 0;
    }
    if (GridPaths.BOARD[prevI - 1][prevJ] == 1 && GridPaths.BOARD[prevI + 1][prevJ] == 1 && GridPaths.BOARD[prevI][prevJ - 1] == 0 && GridPaths.BOARD[prevI][prevJ + 1] == 0) {
      return 0;
    }

    if (currentStrIndex == originalString.length() - 1) {
      if (!(prevI == GridPaths.BOARD_SIZE - 1 && prevJ == 1) && !(prevI == GridPaths.BOARD_SIZE && prevJ == 2)) {
        return 0;
      }
    }

    if (originalString.charAt(currentStrIndex) == 'D') {
      if (GridPaths.BOARD[prevI + 1][prevJ] == 1) {
        return 0;
      } else if (prevI == GridPaths.BOARD_SIZE - 1 && prevJ == 1 && currentStrIndex < originalString.length() - 1) {
        // It's a step to exit cell, but not the last step.
        return 0;
      }
      pathsNumber += GridPaths.goDown(originalString, currentStrIndex, prevI, prevJ);
    } else if (originalString.charAt(currentStrIndex) == 'U') {
      if (GridPaths.BOARD[prevI - 1][prevJ] == 1) {
        return 0;
      }
      pathsNumber += GridPaths.goUp(originalString, currentStrIndex, prevI, prevJ);
    } else if (originalString.charAt(currentStrIndex) == 'L') {
      if (GridPaths.BOARD[prevI][prevJ - 1] == 1) {
        return 0;
      } else if (prevI == GridPaths.BOARD_SIZE && prevJ == 2 && currentStrIndex < originalString.length() - 1) {
        // It's a step to exit cell, but not the last step.
        return 0;
      }
      pathsNumber += GridPaths.goLeft(originalString, currentStrIndex, prevI, prevJ);
    } else if (originalString.charAt(currentStrIndex) == 'R') {
      if (GridPaths.BOARD[prevI][prevJ + 1] == 1) {
        return 0;
      }
      pathsNumber += GridPaths.goRight(originalString, currentStrIndex, prevI, prevJ);
    } else {
      if (GridPaths.BOARD[prevI + 1][prevJ] == 0 && !(prevI == GridPaths.BOARD_SIZE - 1 && prevJ == 1 && currentStrIndex < originalString.length() - 1)) {
        pathsNumber += GridPaths.goDown(originalString, currentStrIndex, prevI, prevJ);
      }
      if (GridPaths.BOARD[prevI - 1][prevJ] == 0) {
        pathsNumber += GridPaths.goUp(originalString, currentStrIndex, prevI, prevJ);
      }
      if (GridPaths.BOARD[prevI][prevJ + 1] == 0) {
        pathsNumber += GridPaths.goRight(originalString, currentStrIndex, prevI, prevJ);
      }
      if (GridPaths.BOARD[prevI][prevJ - 1] == 0 && !(prevI == GridPaths.BOARD_SIZE && prevJ == 2 && currentStrIndex < originalString.length() - 1)) {
        pathsNumber += GridPaths.goLeft(originalString, currentStrIndex, prevI, prevJ);
      }
    }
    return pathsNumber;
  }

  public static int goDown(String originalString, int currentStrIndex, int prevI, int prevJ) {
    GridPaths.BOARD[prevI + 1][prevJ] = 1;
    int result = GridPaths.checkPath(originalString, currentStrIndex + 1, prevI + 1, prevJ);
    GridPaths.BOARD[prevI + 1][prevJ] = 0;
    return result;
  }

  public static int goUp(String originalString, int currentStrIndex, int prevI, int prevJ) {
    GridPaths.BOARD[prevI - 1][prevJ] = 1;
    int result = GridPaths.checkPath(originalString, currentStrIndex + 1, prevI - 1, prevJ);
    GridPaths.BOARD[prevI - 1][prevJ] = 0;
    return result;
  }

  public static int goRight(String originalString, int currentStrIndex, int prevI, int prevJ) {
    GridPaths.BOARD[prevI][prevJ + 1] = 1;
    int result = GridPaths.checkPath(originalString, currentStrIndex + 1, prevI, prevJ + 1);
    GridPaths.BOARD[prevI][prevJ + 1] = 0;
    return result;
  }

  public static int goLeft(String originalString, int currentStrIndex, int prevI, int prevJ) {
    GridPaths.BOARD[prevI][prevJ - 1] = 1;
    int result = GridPaths.checkPath(originalString, currentStrIndex + 1, prevI, prevJ - 1);
    GridPaths.BOARD[prevI][prevJ - 1] = 0;
    return result;
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String inputStr = input.nextLine();
    input.close();
    System.out.println(GridPaths.gridPaths(inputStr));
  }
}


// import java.util.Scanner;

// class GridPaths {
//   public static final int BOARD_SIZE = 7;
//   public static int[][] BOARD = new int[GridPaths.BOARD_SIZE + 2][GridPaths.BOARD_SIZE + 2];
//   public static int NUMBER_OF_STRINGS = 0;
//   public static int NUMBER_OF_CALLS = 0;
//   public static int gridPaths(String originalString) {
//     for (int i = 0; i < GridPaths.BOARD.length; i++) {
//       GridPaths.BOARD[0][i] = 1;
//       GridPaths.BOARD[GridPaths.BOARD_SIZE + 1][i] = 1;
//       GridPaths.BOARD[i][0] = 1;
//       GridPaths.BOARD[i][GridPaths.BOARD_SIZE + 1] = 1;
//     }
//     GridPaths.BOARD[1][1] = 1;
//     int firstExactIndex = -1;
//     int lastExactIndex = -1;
//     for (int i = 0; i < originalString.length(); i++) {
//       if (originalString.charAt(i) != '?' && firstExactIndex == -1) {
//         firstExactIndex = i;
//         break;
//       }
//     }
//     for (int i = originalString.length() - 1; i >= 0; i--) {
//       if (originalString.charAt(i) != '?' && lastExactIndex == -1) {
//         lastExactIndex = i;
//         break;
//       }
//     }
//     System.out.println(firstExactIndex);
//     System.out.println(lastExactIndex);
//     if (firstExactIndex > originalString.length() - lastExactIndex) {
//       originalString = GridPaths.revertString(originalString);
//     }
//     GridPaths.checkPath(originalString, 0, 1, 1);
//     return GridPaths.NUMBER_OF_STRINGS;
//   }

//   public static String revertString(String originalString) {
//     String s = "";
//     for (int i = 0; i < originalString.length(); i++) {
//       switch (originalString.charAt(i)) {
//         case 'L':
//           s = 'R' + s;
//           break;
//         case 'R':
//           s = 'L' + s;
//           break;
//         default:
//           s = originalString.charAt(i) + s;
//       }
//     }
//     System.out.println(s);
//     return s;
//   }

//   public static void checkPath(String originalString, int currentStrIndex, int prevI, int prevJ) {
//     GridPaths.NUMBER_OF_CALLS++;
//     //System.out.println(currentStrIndex);
//     if (currentStrIndex == originalString.length()) {
//       if (prevI == GridPaths.BOARD_SIZE && prevJ == 1) {
//         GridPaths.NUMBER_OF_STRINGS++;
//       }
//       return;
//     }

//     if (GridPaths.BOARD[prevI - 1][prevJ] == 0) {
//       if (GridPaths.BOARD[prevI - 2][prevJ] == 0 && GridPaths.BOARD[prevI - 1][prevJ - 1] == 1 && GridPaths.BOARD[prevI - 1][prevJ + 1] == 1) {
//         if ((originalString.charAt(currentStrIndex) == 'U' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'U' || originalString.charAt(currentStrIndex + 1) == '?')) {
//           GridPaths.BOARD[prevI - 1][prevJ] = 1;
//           GridPaths.BOARD[prevI - 2][prevJ] = 1;
//           GridPaths.checkPath(originalString, currentStrIndex + 2, prevI - 2, prevJ);
//           GridPaths.BOARD[prevI - 1][prevJ] = 0;
//           GridPaths.BOARD[prevI - 2][prevJ] = 0;
//           return;
//         } else {
//           return;
//         }
//       } else if (GridPaths.BOARD[prevI - 2][prevJ] == 1 && GridPaths.BOARD[prevI - 1][prevJ - 1] == 0 && GridPaths.BOARD[prevI - 1][prevJ + 1] == 1) {
//         if ((originalString.charAt(currentStrIndex) == 'U' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'L' || originalString.charAt(currentStrIndex + 1) == '?')) {
//           GridPaths.BOARD[prevI - 1][prevJ] = 1;
//           GridPaths.BOARD[prevI - 1][prevJ - 1] = 1;
//           GridPaths.checkPath(originalString, currentStrIndex + 2, prevI - 1, prevJ - 1);
//           GridPaths.BOARD[prevI - 1][prevJ] = 0;
//           GridPaths.BOARD[prevI - 1][prevJ - 1] = 0;
//           return;
//         } else {
//           return;
//         }        
//       } else if (GridPaths.BOARD[prevI - 2][prevJ] == 1 && GridPaths.BOARD[prevI - 1][prevJ - 1] == 1 && GridPaths.BOARD[prevI - 1][prevJ + 1] == 0) {
//         if ((originalString.charAt(currentStrIndex) == 'U' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'R' || originalString.charAt(currentStrIndex + 1) == '?')) {
//           GridPaths.BOARD[prevI - 1][prevJ] = 1;
//           GridPaths.BOARD[prevI - 1][prevJ + 1] = 1;
//           GridPaths.checkPath(originalString, currentStrIndex + 2, prevI - 1, prevJ + 1);
//           GridPaths.BOARD[prevI - 1][prevJ] = 0;
//           GridPaths.BOARD[prevI - 1][prevJ + 1] = 0;
//           return;
//         } else {
//           return;
//         }
//       }
//     }

//     if (GridPaths.BOARD[prevI + 1][prevJ] == 0) {
//       if (GridPaths.BOARD[prevI + 2][prevJ] == 0 && GridPaths.BOARD[prevI + 1][prevJ - 1] == 1 && GridPaths.BOARD[prevI + 1][prevJ + 1] == 1) {
//         if ((originalString.charAt(currentStrIndex) == 'D' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'D' || originalString.charAt(currentStrIndex + 1) == '?')) {
//           GridPaths.BOARD[prevI + 1][prevJ] = 1;
//           GridPaths.BOARD[prevI + 2][prevJ] = 1;
//           GridPaths.checkPath(originalString, currentStrIndex + 2, prevI + 2, prevJ);
//           GridPaths.BOARD[prevI + 1][prevJ] = 0;
//           GridPaths.BOARD[prevI + 2][prevJ] = 0;
//           return;
//         } else {
//           return;
//         }
//       } else if (GridPaths.BOARD[prevI + 2][prevJ] == 1 && GridPaths.BOARD[prevI + 1][prevJ - 1] == 0 && GridPaths.BOARD[prevI + 1][prevJ + 1] == 1) {
//         if ((originalString.charAt(currentStrIndex) == 'D' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'L' || originalString.charAt(currentStrIndex + 1) == '?')) {
//           GridPaths.BOARD[prevI + 1][prevJ] = 1;
//           GridPaths.BOARD[prevI + 1][prevJ - 1] = 1;
//           GridPaths.checkPath(originalString, currentStrIndex + 2, prevI + 1, prevJ - 1);
//           GridPaths.BOARD[prevI + 1][prevJ] = 0;
//           GridPaths.BOARD[prevI + 1][prevJ - 1] = 0;
//           return;
//         } else {
//           return;
//         }        
//       } else if (GridPaths.BOARD[prevI + 2][prevJ] == 1 && GridPaths.BOARD[prevI + 1][prevJ - 1] == 1 && GridPaths.BOARD[prevI + 1][prevJ + 1] == 0) {
//         if (prevI + 1 == GridPaths.BOARD_SIZE && prevJ == 1) {
//           // It's a step to exit cell.
//         } else {
//           if ((originalString.charAt(currentStrIndex) == 'D' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'R' || originalString.charAt(currentStrIndex + 1) == '?')) {
//             GridPaths.BOARD[prevI + 1][prevJ] = 1;
//             GridPaths.BOARD[prevI + 1][prevJ + 1] = 1;
//             GridPaths.checkPath(originalString, currentStrIndex + 2, prevI + 1, prevJ + 1);
//             GridPaths.BOARD[prevI + 1][prevJ] = 0;
//             GridPaths.BOARD[prevI + 1][prevJ + 1] = 0;
//             return;
//           } else {
//             return;
//           }
//         }
//       }
//     }

//     if (GridPaths.BOARD[prevI][prevJ - 1] == 0) {
//       if (GridPaths.BOARD[prevI][prevJ - 2] == 0 && GridPaths.BOARD[prevI - 1][prevJ - 1] == 1 && GridPaths.BOARD[prevI + 1][prevJ - 1] == 1) {
//         if ((originalString.charAt(currentStrIndex) == 'L' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'L' || originalString.charAt(currentStrIndex + 1) == '?')) {
//           GridPaths.BOARD[prevI][prevJ - 1] = 1;
//           GridPaths.BOARD[prevI][prevJ - 2] = 1;
//           GridPaths.checkPath(originalString, currentStrIndex + 2, prevI, prevJ - 2);
//           GridPaths.BOARD[prevI][prevJ - 1] = 0;
//           GridPaths.BOARD[prevI][prevJ - 2] = 0;
//           return;
//         } else {
//           return;
//         }
//      } else if (GridPaths.BOARD[prevI][prevJ - 2] == 1 && GridPaths.BOARD[prevI - 1][prevJ - 1] == 0 && GridPaths.BOARD[prevI + 1][prevJ - 1] == 1) {
//        if (prevI == GridPaths.BOARD_SIZE && prevJ == 2) {
//          // It's step to exit cell.
//        } else {
//          if ((originalString.charAt(currentStrIndex) == 'L' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'U' || originalString.charAt(currentStrIndex + 1) == '?')) {
//            GridPaths.BOARD[prevI][prevJ - 1] = 1;
//            GridPaths.BOARD[prevI - 1][prevJ - 1] = 1;
//            GridPaths.checkPath(originalString, currentStrIndex + 2, prevI - 1, prevJ - 1);
//            GridPaths.BOARD[prevI][prevJ - 1] = 0;
//            GridPaths.BOARD[prevI - 1][prevJ - 1] = 0;
//            return;
//          } else {
//            return;
//          }
//        }        
//       } else if (GridPaths.BOARD[prevI][prevJ - 2] == 1 && GridPaths.BOARD[prevI - 1][prevJ - 1] == 1 && GridPaths.BOARD[prevI + 1][prevJ - 1] == 0) {
//         if ((originalString.charAt(currentStrIndex) == 'L' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'D' || originalString.charAt(currentStrIndex + 1) == '?')) {
//           GridPaths.BOARD[prevI][prevJ - 1] = 1;
//           GridPaths.BOARD[prevI + 1][prevJ - 1] = 1;
//           GridPaths.checkPath(originalString, currentStrIndex + 2, prevI + 1, prevJ - 1);
//           GridPaths.BOARD[prevI][prevJ - 1] = 0;
//           GridPaths.BOARD[prevI + 1][prevJ - 1] = 0;
//           return;
//         } else {
//           return;
//         }
//       }
//     }

//     if (GridPaths.BOARD[prevI][prevJ + 1] == 0) {
//       if (GridPaths.BOARD[prevI][prevJ + 2] == 0 && GridPaths.BOARD[prevI - 1][prevJ + 1] == 1 && GridPaths.BOARD[prevI + 1][prevJ + 1] == 1) {
//         if ((originalString.charAt(currentStrIndex) == 'R' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'R' || originalString.charAt(currentStrIndex + 1) == '?')) {
//           GridPaths.BOARD[prevI][prevJ + 1] = 1;
//           GridPaths.BOARD[prevI][prevJ + 2] = 1;
//           GridPaths.checkPath(originalString, currentStrIndex + 2, prevI, prevJ + 2);
//           GridPaths.BOARD[prevI][prevJ + 1] = 0;
//           GridPaths.BOARD[prevI][prevJ + 2] = 0;
//           return;
//         } else {
//           return;
//         }
//       } else if (GridPaths.BOARD[prevI][prevJ + 2] == 1 && GridPaths.BOARD[prevI - 1][prevJ + 1] == 0 && GridPaths.BOARD[prevI + 1][prevJ + 1] == 1) {
//         if ((originalString.charAt(currentStrIndex) == 'R' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'U' || originalString.charAt(currentStrIndex + 1) == '?')) {
//           GridPaths.BOARD[prevI][prevJ + 1] = 1;
//           GridPaths.BOARD[prevI - 1][prevJ + 1] = 1;
//           GridPaths.checkPath(originalString, currentStrIndex + 2, prevI - 1, prevJ + 1);
//           GridPaths.BOARD[prevI][prevJ + 1] = 0;
//           GridPaths.BOARD[prevI - 1][prevJ + 1] = 0;
//           return;
//         } else {
//           return;
//         }        
//       } else if (GridPaths.BOARD[prevI][prevJ + 2] == 1 && GridPaths.BOARD[prevI - 1][prevJ + 1] == 1 && GridPaths.BOARD[prevI + 1][prevJ + 1] == 0) {
//         if ((originalString.charAt(currentStrIndex) == 'R' || originalString.charAt(currentStrIndex) == '?') && (originalString.charAt(currentStrIndex + 1) == 'D' || originalString.charAt(currentStrIndex + 1) == '?')) {
//           GridPaths.BOARD[prevI][prevJ + 1] = 1;
//           GridPaths.BOARD[prevI + 1][prevJ + 1] = 1;
//           GridPaths.checkPath(originalString, currentStrIndex + 2, prevI + 1, prevJ + 1);
//           GridPaths.BOARD[prevI][prevJ + 1] = 0;
//           GridPaths.BOARD[prevI + 1][prevJ + 1] = 0;
//           return;
//         } else {
//           return;
//         }
//       }
//     }

//     if (prevI == GridPaths.BOARD_SIZE - 1 && prevJ == 1) {
//       if (currentStrIndex < originalString.length() - 1) {
//         if (originalString.charAt(currentStrIndex) == 'D') {
//           return;
//         }
//       } else {
//         if (originalString.charAt(currentStrIndex) != 'D' && originalString.charAt(currentStrIndex) != '?') {
//           return;
//         } else {
//           GridPaths.NUMBER_OF_STRINGS++;
//           return;
//         }
//       }
//     }

//     if (prevI == GridPaths.BOARD_SIZE && prevJ == 2) {
//       if (currentStrIndex < originalString.length() - 1) {
//         if (originalString.charAt(currentStrIndex) == 'L') {
//           return;
//         }
//       } else {
//         if (originalString.charAt(currentStrIndex) != 'L' && originalString.charAt(currentStrIndex) != '?') {
//           return;
//         } else {
//           GridPaths.NUMBER_OF_STRINGS++;
//           return;
//         }
//       }
//     }

//     if (prevI == GridPaths.BOARD_SIZE && prevJ == 1 && currentStrIndex < originalString.length() - 1) {
//       return;
//     }

//     if (GridPaths.BOARD[prevI - 1][prevJ] == 0 && GridPaths.BOARD[prevI + 1][prevJ] == 0 && GridPaths.BOARD[prevI][prevJ - 1] == 1 && GridPaths.BOARD[prevI][prevJ + 1] == 1) {
//       return;
//     }
//     if (GridPaths.BOARD[prevI - 1][prevJ] == 1 && GridPaths.BOARD[prevI + 1][prevJ] == 1 && GridPaths.BOARD[prevI][prevJ - 1] == 0 && GridPaths.BOARD[prevI][prevJ + 1] == 0) {
//       return;
//     }

//     if (currentStrIndex == originalString.length() - 1) {
//       if (!(prevI == GridPaths.BOARD_SIZE - 1 && prevJ == 1) && !(prevI == GridPaths.BOARD_SIZE && prevJ == 2)) {
//         return;
//       }
//     }

//     if (originalString.charAt(currentStrIndex) == 'D') {
//       if (GridPaths.BOARD[prevI + 1][prevJ] == 1) {
//         return;
//       } else if (prevI == GridPaths.BOARD_SIZE - 1 && prevJ == 1 && currentStrIndex < originalString.length() - 1) {
//         // It's a step to exit cell, but not the last step.
//         return;
//       }
//       GridPaths.goDown(originalString, currentStrIndex, prevI, prevJ);
//     } else if (originalString.charAt(currentStrIndex) == 'U') {
//       if (GridPaths.BOARD[prevI - 1][prevJ] == 1) {
//         return;
//       }
//       GridPaths.goUp(originalString, currentStrIndex, prevI, prevJ);
//     } else if (originalString.charAt(currentStrIndex) == 'L') {
//       if (GridPaths.BOARD[prevI][prevJ - 1] == 1) {
//         return;
//       } else if (prevI == GridPaths.BOARD_SIZE && prevJ == 2 && currentStrIndex < originalString.length() - 1) {
//         // It's a step to exit cell, but not the last step.
//         return;
//       }
//       GridPaths.goLeft(originalString, currentStrIndex, prevI, prevJ);
//     } else if (originalString.charAt(currentStrIndex) == 'R') {
//       if (GridPaths.BOARD[prevI][prevJ + 1] == 1) {
//         return;
//       }
//       GridPaths.goRight(originalString, currentStrIndex, prevI, prevJ);
//     } else {
//       if (GridPaths.BOARD[prevI + 1][prevJ] == 0 && !(prevI == GridPaths.BOARD_SIZE - 1 && prevJ == 1 && currentStrIndex < originalString.length() - 1)) {
//         GridPaths.goDown(originalString, currentStrIndex, prevI, prevJ);
//       }
//       if (GridPaths.BOARD[prevI - 1][prevJ] == 0) {
//         GridPaths.goUp(originalString, currentStrIndex, prevI, prevJ);
//       }
//       if (GridPaths.BOARD[prevI][prevJ + 1] == 0) {
//         GridPaths.goRight(originalString, currentStrIndex, prevI, prevJ);
//       }
//       if (GridPaths.BOARD[prevI][prevJ - 1] == 0 && !(prevI == GridPaths.BOARD_SIZE && prevJ == 2 && currentStrIndex < originalString.length() - 1)) {
//         GridPaths.goLeft(originalString, currentStrIndex, prevI, prevJ);
//       }
//     }
//   }

//   public static void goDown(String originalString, int currentStrIndex, int prevI, int prevJ) {
//     GridPaths.BOARD[prevI + 1][prevJ] = 1;
//     GridPaths.checkPath(originalString, currentStrIndex + 1, prevI + 1, prevJ);
//     GridPaths.BOARD[prevI + 1][prevJ] = 0;
//   }

//   public static void goUp(String originalString, int currentStrIndex, int prevI, int prevJ) {
//     GridPaths.BOARD[prevI - 1][prevJ] = 1;
//     GridPaths.checkPath(originalString, currentStrIndex + 1, prevI - 1, prevJ);
//     GridPaths.BOARD[prevI - 1][prevJ] = 0;
//   }

//   public static void goRight(String originalString, int currentStrIndex, int prevI, int prevJ) {
//     GridPaths.BOARD[prevI][prevJ + 1] = 1;
//     GridPaths.checkPath(originalString, currentStrIndex + 1, prevI, prevJ + 1);
//     GridPaths.BOARD[prevI][prevJ + 1] = 0;
//   }

//   public static void goLeft(String originalString, int currentStrIndex, int prevI, int prevJ) {
//     GridPaths.BOARD[prevI][prevJ - 1] = 1;
//     GridPaths.checkPath(originalString, currentStrIndex + 1, prevI, prevJ - 1);
//     GridPaths.BOARD[prevI][prevJ - 1] = 0;
//   }

//   public static void main(String[] args) {
//     Scanner input = new Scanner(System.in);
//     String inputStr = input.nextLine();
//     input.close();
//     long time1 = new java.util.Date().getTime();
//     System.out.println(GridPaths.gridPaths(inputStr));
//     System.out.println(new java.util.Date().getTime() - time1);
//     System.out.println(GridPaths.NUMBER_OF_CALLS);
//   }
// }