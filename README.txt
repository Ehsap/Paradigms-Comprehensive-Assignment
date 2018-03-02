To parse the original wading-pools JSON file into a more usable format, I incorporated the following Java libraries.

	1. GSON https://github.com/google/gson
	2. GEOGSON https://github.com/filosganga/geogson

Both of these libraries assist in parsing JSON data and converting it into java objects and vice versa.
For part 1 of the assignment, I parsed the JSON data to minimize to the two essential fields, NAME and COORDINATES.
This parsing step produced a JSON file called wading-pools-min.JSON. 
