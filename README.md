# Potter's Kata

<h2>Problem:</h2>

Once upon a time there was a series of 5 books about a very English hero called Harry. (At least when this Kata was invented, there were only 5. Since then they have multiplied) Children all over the world thought he was fantastic, and, of course, so did the publisher. So in a gesture of immense generosity to mankind, (and to increase sales) they set up the following pricing model to take advantage of Harry’s magical powers.
One copy of any of the five books costs 8 EUR. If, however, you buy two different books from the series, you get a 5% discount on those two books. If you buy 3 different books, you get a 10% discount. With 4 different books, you get a 20% discount. If you go the whole hog, and buy all 5, you get a huge 25% discount.
Note that if you buy, say, four books, of which 3 are different titles, you get a 10% discount on the 3 that form part of a set, but the fourth book still costs 8 EUR.
Potter mania is sweeping the country and parents of teenagers everywhere are queueing up with shopping baskets overflowing with Potter books. Your mission is to write a piece of code to calculate the price of any conceivable shopping basket, giving as big a discount as possible.

For example, how much does this basket of books cost?
<ul>
<li>2 copies of the first book</li>
<li>2 copies of the second book</li>
<li>2 copies of the third book</li>
<li>1 copy of the fourth book</li>
<li>1 copy of the fifth book</li>
</ul>
answer:
<br>
<code>(4 * 8) - 20% [first book, seconde book, third book, fourth book] + (4 * 8) - 20% [first book, seconde book, third book, fifth book] = 25.6 * 2 = 51.20</code>

<small>source: <a href="http://codingdojo.org/kata/Potter/">codingdojo.org/kata/potter</a></small>

# Java Implementation
The basic idea is to implement a basket calculator which will process the final price for the books in the shopping basket after verifying and applying rebates.
This solution was built in Java following <a href="https://en.wikipedia.org/wiki/Test-driven_development">Test Driven Development (TDD)</a> concepts and using <a href="http://junit.org/junit4/">JUnit</a> for testing.
Defined test cases were retired from the original page, but I also specified some test cases myself.
