#include <stdio.h>
#include <stdlib.h>
#include <math.h>

static const int N = 1024;

void matmul(float* A, float* B, float* C) {
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            C[i * N + j] = 0;
            for(int k = 0; k < N; k++) {
                C[i * N + j] += A[i * N + k] * B[k * N + j];
            }
        }
    }
}

void softmax(float* C, float* D) {
    for(int i = 0; i < N; i++) {
        float max = C[i * N];
        for(int j = 1; j < N; j++) {
            if(C[i * N + j] > max) {
                max = C[i * N + j];
            }
        }
        for(int j = 0; j < N; j++) {
            D[i * N + j] = expf(C[i * N + j] - max);
        }
        float sum = 0;
        for(int j = 0; j < N; j++) {
            sum += D[i * N + j];
        }
        for(int j = 0; j < N; j++) {
            D[i * N + j] /= sum;
        }
    }
}

void maxindex(float* D, int* E) {
    for(int i = 0; i < N; i++) {
        int max_index = 0;
        float max_value = D[i * N];
        for(int j = 1; j < N; j++) {
            if(D[i * N + j] > max_value) {
                max_value = D[i * N + j];
                max_index = j;
            }
        }
        E[i] = max_index;
    }
}

void compute(float* A, float* B, int* E) {
    float* C = malloc(N * N * sizeof(float));
    float* D = malloc(N * N * sizeof(float));

    matmul(A, B, C);
    softmax(C, D);
    maxindex(D, E);

    free(C);
    free(D);
}

int main() {
    float* A = malloc(N * N * sizeof(float));
    float* B = malloc(N * N * sizeof(float));
    int* E   = malloc(N * sizeof(int));

    unsigned int seed = 42;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            seed = seed * 1103515245 + 12345;
            A[i * N + j] = (float)(seed % 1000) / 1000.0f - 0.5f;
            seed = seed * 1103515245 + 12345;
            B[i * N + j] = (float)(seed % 1000) / 1000.0f - 0.5f;
        }
    }

    compute(A, B, E);

    long long sum = 0;
    for(int i = 0; i < N; i++) {
        sum += E[i];
    }
    printf("Sum of max indices (per softmax row): %lld\n", sum);

    free(A);
    free(B);
    free(E);
    return 0;
}

