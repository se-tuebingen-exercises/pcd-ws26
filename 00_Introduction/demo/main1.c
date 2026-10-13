#include <stdio.h>

#define SIZE 1000

double a[SIZE][SIZE];
double b[SIZE][SIZE];
double c[SIZE][SIZE];

int main() {
  for (int i = 0; i < SIZE; i++) {
    for (int j = 0; j < SIZE; j++) {
      a[i][j] = i + j;
      b[i][j] = i - j;
    }
  }

  for (int i = 0; i < SIZE; i++) {
    for (int j = 0; j < SIZE; j++) {
      for (int k = 0; k < SIZE; k++) {
        c[i][j] += a[i][k] * b[k][j];
      }
    }
  }

  double checksum = 0;
  for (int i = 0; i < SIZE; i++) {
    for (int j = 0; j < SIZE; j++) {
      checksum += c[i][j];
    }
  }
  printf("%f\n", checksum);
}
