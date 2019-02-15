var array = [1, 2, 3, 4, 5]
var sum = 0;

array.forEach(function(element) {
    sum += element;
});
console.log(sum);

function square(num) {
    return num * num;
}
var array2 = array.map(square);
console.log(array2);

function isEven(num) {
    return num % 2 == 0;
}
var array3 = array.filter(isEven);
console.log(array3);

var allEven = array.every(isEven);
console.log(allEven);

var someEven = array.some(isEven);
console.log(someEven);

function getSum(total, num) {
    return total + num;
}
var sum = array.reduce(getSum);
console.log(sum);