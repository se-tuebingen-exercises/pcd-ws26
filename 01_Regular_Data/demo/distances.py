import time
import torch

size = 100
points = torch.randn(size, 2)
distances = torch.zeros(size, size)

start = time.time()

for i in range(size):
    for j in range(size):
        distances[i, j] = torch.linalg.vector_norm(points[i] - points[j])

elapsed = time.time() - start
print(f"{elapsed:.6f}")

print(distances.max())

start = time.time()

points0 = points.unsqueeze(1)
points1 = points.unsqueeze(0)

distances = torch.linalg.vector_norm(points0 - points1, dim=2)

elapsed = time.time() - start
print(f"{elapsed:.6f}")

print(distances.max())

