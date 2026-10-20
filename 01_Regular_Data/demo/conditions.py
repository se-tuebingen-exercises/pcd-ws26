import torch

x = torch.tensor([-1, 2, -3, -4, 5, 6])

mask = x > 0

filtered = torch.where(mask, x, torch.tensor(0))

result = filtered.sum()

print(result)

