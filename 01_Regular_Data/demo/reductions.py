import time
import torch

x = torch.tensor([[0, 1, 2], [3, 4, 5], [6, 7, 8], [9, 10, 11]])

print(x.sum(dim=1))
print(x.sum(dim=0))
print(x.sum(dim=(0, 1)))

print(x.amax(dim=1))
print(x.amax(dim=0))
print(x.amax(dim=(0, 1)))

print(x.amax(dim=0, keepdim=True).shape)
print(x.amax(dim=0, keepdim=True))

print(x.max(dim=1))
print(x.max(dim=0))
print(x.max())

size = 10_000
a = torch.rand(size, size)
start = time.time()

for _ in range(10):
    b = a.sum(dim=0).max()

elapsed = time.time() - start
print(f"{elapsed:.6f}")
print(b)

