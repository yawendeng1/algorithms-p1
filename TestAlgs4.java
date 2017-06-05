<!-- Generator: GNU source-highlight 3.1.6
by Lorenzo Bettini
http://www.lorenzobettini.it
http://www.gnu.org/software/src-highlite -->
<pre><tt><i><font color="#9A1900">/******************************************************************************</font></i>
<i><font color="#9A1900"> *  Compilation:  javac-algs4 TestAlgs4.java</font></i>
<i><font color="#9A1900"> *  Execution:    java-algs4 TestAlgs4 </font></i>
<i><font color="#9A1900"> *  Dependencies: StdDraw.java</font></i>
<i><font color="#9A1900"> *                </font></i><u><font color="#0000FF">http://algs4.cs.princeton.edu/mac/cover.jpg</font></u>
<i><font color="#9A1900"> *  </font></i>
<i><font color="#9A1900"> *  Draws a blue bullseye and textbook graphic.</font></i>
<i><font color="#9A1900"> * </font></i>
<i><font color="#9A1900"> ******************************************************************************/</font></i>

<b><font color="#000080">import</font></b> edu<font color="#990000">.</font>princeton<font color="#990000">.</font>cs<font color="#990000">.</font>algs4<font color="#990000">.</font>StdDraw<font color="#990000">;</font>

<b><font color="#0000FF">public</font></b> <b><font color="#0000FF">class</font></b> <font color="#008080">TestAlgs4</font> <font color="#FF0000">{</font>
    <b><font color="#0000FF">public</font></b> <b><font color="#0000FF">static</font></b> <font color="#009900">void</font> <b><font color="#000000">main</font></b> <font color="#990000">(</font>String<font color="#990000">[]</font> args<font color="#990000">)</font> <font color="#FF0000">{</font>
        StdDraw<font color="#990000">.</font><b><font color="#000000">setScale</font></b><font color="#990000">(-</font><font color="#993399">1</font><font color="#990000">,</font> <font color="#993399">1</font><font color="#990000">);</font>
        StdDraw<font color="#990000">.</font><b><font color="#000000">clear</font></b><font color="#990000">(</font>StdDraw<font color="#990000">.</font>BLACK<font color="#990000">);</font>
        
        StdDraw<font color="#990000">.</font><b><font color="#000000">setPenColor</font></b><font color="#990000">(</font>StdDraw<font color="#990000">.</font>WHITE<font color="#990000">);</font>
        StdDraw<font color="#990000">.</font><b><font color="#000000">square</font></b><font color="#990000">(</font><font color="#993399">0</font><font color="#990000">,</font> <font color="#993399">0</font><font color="#990000">,</font> <font color="#993399">1</font><font color="#990000">);</font>
        
        <i><font color="#9A1900">// write some text</font></i>
        StdDraw<font color="#990000">.</font><b><font color="#000000">setPenColor</font></b><font color="#990000">(</font>StdDraw<font color="#990000">.</font>WHITE<font color="#990000">);</font>
        StdDraw<font color="#990000">.</font><b><font color="#000000">text</font></b><font color="#990000">(</font><font color="#993399">0</font><font color="#990000">,</font> <font color="#990000">+</font><font color="#993399">0.95</font><font color="#990000">,</font> <font color="#FF0000">"Hello, world! This is a test Java program."</font><font color="#990000">);</font>
        StdDraw<font color="#990000">.</font><b><font color="#000000">text</font></b><font color="#990000">(</font><font color="#993399">0</font><font color="#990000">,</font> <font color="#990000">-</font><font color="#993399">0.95</font><font color="#990000">,</font> <font color="#FF0000">"Close this window to finish the installation."</font><font color="#990000">);</font>
        
        <i><font color="#9A1900">// draw the bullseye</font></i>
        StdDraw<font color="#990000">.</font><b><font color="#000000">setPenColor</font></b><font color="#990000">(</font>StdDraw<font color="#990000">.</font>BOOK_BLUE<font color="#990000">);</font>
        StdDraw<font color="#990000">.</font><b><font color="#000000">filledCircle</font></b><font color="#990000">(</font><font color="#993399">0</font><font color="#990000">,</font> <font color="#993399">0</font><font color="#990000">,</font> <font color="#993399">0.9</font><font color="#990000">);</font>
        StdDraw<font color="#990000">.</font><b><font color="#000000">setPenColor</font></b><font color="#990000">(</font>StdDraw<font color="#990000">.</font>BLACK<font color="#990000">);</font>
        StdDraw<font color="#990000">.</font><b><font color="#000000">filledCircle</font></b><font color="#990000">(</font><font color="#993399">0</font><font color="#990000">,</font> <font color="#993399">0</font><font color="#990000">,</font> <font color="#993399">0.8</font><font color="#990000">);</font>
        StdDraw<font color="#990000">.</font><b><font color="#000000">setPenColor</font></b><font color="#990000">(</font>StdDraw<font color="#990000">.</font>BOOK_BLUE<font color="#990000">);</font>
        StdDraw<font color="#990000">.</font><b><font color="#000000">filledCircle</font></b><font color="#990000">(</font><font color="#993399">0</font><font color="#990000">,</font> <font color="#993399">0</font><font color="#990000">,</font> <font color="#993399">0.7</font><font color="#990000">);</font>
        StdDraw<font color="#990000">.</font><b><font color="#000000">setPenColor</font></b><font color="#990000">(</font>StdDraw<font color="#990000">.</font>BLACK<font color="#990000">);</font>
        StdDraw<font color="#990000">.</font><b><font color="#000000">filledCircle</font></b><font color="#990000">(</font><font color="#993399">0</font><font color="#990000">,</font> <font color="#993399">0</font><font color="#990000">,</font> <font color="#993399">0.6</font><font color="#990000">);</font>

        <i><font color="#9A1900">// draw a picture of the textbook        </font></i>
        StdDraw<font color="#990000">.</font><b><font color="#000000">picture</font></b><font color="#990000">(</font><font color="#993399">0</font><font color="#990000">,</font> <font color="#993399">0</font><font color="#990000">,</font> <font color="#FF0000">"cover.jpg"</font><font color="#990000">,</font> <font color="#993399">0.65</font><font color="#990000">,</font> <font color="#993399">0.80</font><font color="#990000">);</font>
    <font color="#FF0000">}</font>
<font color="#FF0000">}</font>
</tt></pre>
