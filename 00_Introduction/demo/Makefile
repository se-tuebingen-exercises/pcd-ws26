all: main1 main2 main3

hello:
	cowsay "hello"

bench_chronos:
	chronos './main1' './main2'

bench_hyperfine:
	hyperfine -w 10 --export-csv results.csv './main1' './main2'

main1: main1.c
	gcc -O2 main1.c -o main1

main2: main2.c
	gcc -fopenmp -O2 main2.c -o main2

main3: main3.c
	gcc -O2 main3.c -o main3

clean:
	rm -f main1 main2 main3
