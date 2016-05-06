/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/


// game loop
while (true) {
    var max = 0;
    var maxIndex = 0;
    for (var i = 0; i < 8; i++) {
        var mountainH = parseInt(readline()); // represents the height of one mountain, from 9 to 0.
        if (mountainH > max){
            max = mountainH;
            maxIndex = i;
        }
    }

    // Write an action using print()
    // To debug: printErr('Debug messages...');

    print(maxIndex); // The number of the mountain to fire on.
}