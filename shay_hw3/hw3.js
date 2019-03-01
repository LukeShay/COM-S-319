var rs = require('readline-sync');

var num1 = rs.question('1st Number: ');
var num2 = rs.question('2nd Number: ');
var num3 = rs.question('3rd Number: ');
var num4 = rs.question('4th Number: ');

console.log();

console.log("Factorial of", num1 + ":", factorial(num1));
console.log("Sum of", num2, "digits:", sumNums(num2));
console.log(num3, "reversed:", reverse(num3));
console.log(num4, "is a palindrome:", isPalindrome(num4));

function factorial(n) {
    var result = 1;
    for (var i = n[0]; i > 0; i--)
        result *= i;

    return result;
}

function sumNums(n) {
    var sum = 0;
    for (var i = 0; i < n.length; i++)
        sum += parseInt(n[i], 10);

    return sum;
}

function reverse(n) {
    var sum = n[n.length - 1];
    for (var i = n.length - 2; i >= 0; i--)
        sum += n[i];

    return sum;
}

function isPalindrome(n) {
    for (var i = 0, j = n.length - 1; i < j; i++, j--) {
        if (n[i] != n[j]) return false;
    }
    return true;
}