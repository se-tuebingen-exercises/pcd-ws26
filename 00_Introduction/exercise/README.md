# Exercise 00: Introduction

## Install Nix

Follow https://nix.dev/install-nix

Enable nix-command and flakes in file `~/.config/nix/nix.conf`:

```
experimental-features = nix-command flakes
```

Enter the development shell:

```
nix develop
```

Try out the `cowsay` command:

```
cowsay "hello"
```

## Install VSCode

Follow https://code.visualstudio.com/docs/setup/setup-overview.

Install the "Live Share" extension from the marketplace.

Sign in to Live Share with your GitHub or Microsoft account.

## Benchmarking

Consider file `main.c`, which performs matrix multiplication, followed by
softmax, followed by finding the index of the maximum element in each row. As a
check we then output the sum of these indices.

Enter the nix development shell:

```
nix develop
```

Build the program:

```
gcc -O3 main.c -o main -lm
```

Run the program:

```
./main
```

Measure the time it takes with `hyperfine`:

```
hyperfine './main'
```

Optimize the running time of function `compute` without changing its result.

Continually benchmark all intermediate versions and bring your final version to
the next lab. Keep track of which optimizations gave the biggest speedups.

