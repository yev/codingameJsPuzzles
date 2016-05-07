


/**
 * Don't let the machines win. You are humanity's last hope...
 **/

var width = parseInt(readline()); // the number of cells on the X axis
var height = parseInt(readline()); // the number of cells on the Y axis

var arr = [];
for (var y = 0; y < height; y++) {
	arr[y] = [];
    var line = readline(); // width characters, each either 0 or .
    for (var x = 0; x < line.length; x++ ){
    	arr[y][x] = line.charAt(x);
    }
}

for (var y = 0; y < arr.length; y++) {
    for (var x = 0; x < arr[y].length; x++ ){
    	var noed = arr[y][x];
    	if (noed === '0'){
    		var res = '';
    		res =res + x.toString()+' ';
    		res =res + y.toString()+' ';
    		res = res + findRight(x,y);
    		res =res + findDown(x,y);
    		print(res);
    	}
    }
}


function findRight(x, y){
    if (x == width -1 ){
        return '-1 -1 ';
    }else{
    	for (var i = ++x; i < width; i++){
    		if (arr[y][i] === '0'){
    			return ''+i+' '+y+' ';
    		}
    	}
    	return ' -1 -1 ';
    }
}

function findDown(x,y){
	if (y == height -1){
		return '-1 -1';
	}else{
		for (var i = ++y; i < height; i++){
			if (arr[i][x] === '0'){
				return x+' '+i+' ';
			}
		}
		return ' -1 -1 ';
		
	}
}


