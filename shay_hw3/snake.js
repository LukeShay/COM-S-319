var ctx;
var direction;
var x = -10;
var y = 0;
var canvasWidth;
var canvasHeight;
var snakeWidth = 10;
var started = false;

function start() {
    if (!started) {
        direction = 'right';
        var canvas = document.getElementById("myCanvas");
        ctx = canvas.getContext("2d");
        ctx.fillStyle = "#FF0000";
        canvasWidth = canvas.width;
        canvasHeight = canvas.height;

        var gameloop = setInterval(paint, 1000);
        started = true;
    }
}

var paint = function() {
    switch (direction) {
        case 'right':
            if (x == canvasWidth - snakeWidth) break;
            ctx.fillRect(x += snakeWidth, y, snakeWidth, snakeWidth);
            break;
        case 'left':
            if (x == 0) break;
            ctx.fillRect(x -= snakeWidth, y, snakeWidth, snakeWidth);
            break;
        case 'up':
            if (y == 0) break;
            ctx.fillRect(x, y -= snakeWidth, snakeWidth, snakeWidth);
            break;
        case 'down':
            if (y == canvasHeight - snakeWidth) break;
            ctx.fillRect(x, y += snakeWidth, snakeWidth, snakeWidth);
            break;
    }
}

function left() {
    switch (direction) {
        case 'left':
            direction = 'down';
            break;
        case 'right':
            direction = 'up';
            break;
        case 'down':
            direction = 'right';
            break;
        case 'up':
            direction = 'left';
            break;
    }
    console.log(direction);
}

function right() {
    console.log("right");
    switch (direction) {
        case 'right':
            direction = 'down';
            break;
        case 'left':
            direction = 'up';
            break;
        case 'up':
            direction = 'right';
            break;
        case 'down':
            direction = 'left';
            break;
    }
    console.log(direction);
}