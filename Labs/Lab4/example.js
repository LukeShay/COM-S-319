var rs = require('readline-sync');

var fNum1 = rs.question('1st Number: ');
var fNum2 = rs.question('2nd Number: ');
var action = rs.question('Enter the action{+,-,*,/,%}');

var bits1 = [],
    bits2 = [];

for (var i = 0; i < 5; i++)
    bits1[i] = (fNum1 >> i) & 1;

for (var i = 0; i < 5; i++)
    bits2[i] = (fNum2 >> i) & 1;

// 3. Binary operator “+” represents addition operation.
// 4. Binary operator “*” represents multiplication.
// 5. Binary operator “/” represents division.
// 6. Binary operator “%” represents mod or remainder (i.e. divide the first value by the second,
// what is remaining, only works on positive numbers).
// 7. Binary operator “&” represents AND (only works on positive numbers) e.g. (101 & 1011
// gives 0001)
// 8. Binary operator “|” represents OR (only works on positive numbers) e.g. (101 | 1010 gives
// 1111)
// 9. Unary operator “~” represents not (i.e. invert each bit of the binary value, only works on
// positive numbers) e.g. (101 ~ gives 10).

var result = fNum1 + action + fNum2

var result = eval(result);

console.log(result);