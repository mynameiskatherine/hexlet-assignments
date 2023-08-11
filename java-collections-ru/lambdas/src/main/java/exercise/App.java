package exercise;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

// BEGIN
public class App {
    public static void main(String[] args) {
            String[][] image = {
                    {"*", "*"},
                    {"*", "*"},
            };
            String[][] result = App.enlargeArrayImage(image);
        System.out.println(Arrays.deepToString(result));
    }

    public static String[][] enlargeArrayImage(String[][] image) {
//        String[][] enlargedImage = new String[image.length * 2][image[0].length * 2];
//        String[][] enlargedImage = Arrays.stream(image)
//                .flatMap( line -> Arrays.stream(line) )
//                .forEach( e[i][j] -> Arrays.fill(enlargedImage[i], j * 2, j * 2 + 1, e[i][j]) )
////                .map( e[i][j] -> e[i * 2][j * 2 + 1].copy )
////                .map( e[i][j] -> e[i * 2 + 1][j * 2].copy )
////                .map( e[i][j] -> e[i * 2 + 1][j * 2 + 1].copy )
//                .toArray(String[i][j]:: new);
//        return enlargedImage;
//
//
        if (image == null) {
            return null;
        }
        if (image.length == 0 || image[0].length == 0) {
            return image;
        }
        String[][] enlargedImage =  Arrays.stream(image)
                .map( row -> Arrays.stream(row)
                        .map( elem -> Stream.of(elem, elem))
                        .flatMap(Function.identity())
                        .toArray(String[]::new))
                .map( row -> Stream.of(row, row))
                .flatMap(Function.identity())
                .toArray(String[][]::new);

        return enlargedImage;
    }
}
// END
