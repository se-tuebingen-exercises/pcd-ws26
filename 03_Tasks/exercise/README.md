# Exercise 03: Task Parallelism

## Benchmark Sorting

Compare the running time of the parallel mergesort implementation discussed in
the lecture with `Arrays.sort` and `Arrays.parallelSort`. Compare sorting
strings and sorting integers. Measure execution time for different array sizes.

Use file `Mergesort.java` as a starting point. Feel free to improve its
performance, but always measure and note the effect of the optimization.

Optional challenge: Compare with a multicore radix sort implemented in Futhark,
or a sequential sort implemented in C.

## Counting Words

Download 20 books from Project Gutenberg in parallel and count word frequencies
across all books.

Given a list of URLs in `books.csv`, download each book in parallel, tokenize
the text (convert to lowercase, remove punctuation), and count how often each
word appears in that book. Each task should return a `HashMap<String, Integer>`
with word counts for one book. Merge all hashmaps to get the global word count
and output the 10 most frequent words.

Use file `WordCount.java` as a starting point and feel free to use whatever the
Java standard library offers, as long as you can explain it.

## Image Pipeline

Download a set of images from given URLs, convert them to grayscale, and save
them to disk.

Given a file `images.csv` with image URLs, implement a parallel pipeline with
the following stages:

- Feeder: read the URLs from the file and enqueue them for downloading.

- Downloader: download images from the URLs and enqueue them for conversion.

- Converter: convert each downloaded image to grayscale and enqueue them for saving.

- Saver: write each grayscale image to the `out/` folder with numbered filenames.

Use file `ImagePipeline.java` as a starting point and feel free to use whatever the
Java standard library offers, as long as you can explain it.

