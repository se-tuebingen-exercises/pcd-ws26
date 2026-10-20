import time
import torch

values = torch.tensor([3, 8, 1, 3, 4, 9, 2, 5])
# values = torch.randn(1_000_000)

start = time.time()
result = [0] * (len(values))
for i in range(1, len(values) - 1):
    result[i] = (1/3) * values[i - 1].item() + (1/3) * values[i].item() + (1/3) * values[i + 1].item()

elapsed = time.time() - start
print(f"{elapsed:.6f}")
print(result)

start = time.time()
kernel = torch.tensor([1/3, 1/3, 1/3])
windows = values.unfold(0, 3, 1)
result = (kernel * windows).sum(dim=1)

elapsed = time.time() - start
print(f"{elapsed:.6f}")
print(result)

