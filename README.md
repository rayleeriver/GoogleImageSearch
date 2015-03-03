# GoogleImageSearch
Google Image Search

Time spent: 6 hours in total

Completed user stories:
<ul>
<li>User can enter a search query that will display a grid of image results from the Google Image API.</li>
<li>User can click on "settings" which allows selection of advanced search options to filter results</li
<li>User can configure advanced search filters such as:</li>
<ul>
<li>Size (small, medium, large, extra-large)</li>
<li>Color filter (black, blue, brown, gray, green, etc...)</li>
<li>Type (faces, photo, clip art, line art)</li>
<li>Site (espn.com)</li>
</ul>
<li>Subsequent searches will have any filters applied to the search results</li>
<li>User can tap on any image in results to see the image full-screen</li
<li>User can scroll down “infinitely” to continue loading more image results (up to 8 pages)</li>
</ul>

Completed optional user stories:
<ul>
<li>Advanced: Use the ActionBar SearchView or custom layout as the query box instead of an EditText</li>
<li>Advanced: Replace Filter Settings Activity with a lightweight modal overlay</li>
<li>Advanced: Improve the user interface and experiment with image assets and/or styling and coloring</li>
<li>Bonus: Use the StaggeredGridView to display improve the grid of image results</li>
</ul>

NOTE:  With Picasso 2.5.0, there is an outstanding problem with JPEG image markers for 
some JPEG files.  I've switched to use Universal Image Loader because Picasso doesn't provide any info via onError().

Licecap <a href="https://github.com/rayleeriver/GoogleImageSearch/blob/master/submission_w2.gif">submission</a>