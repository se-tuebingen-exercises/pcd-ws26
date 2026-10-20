import torch

r = torch.zeros(5, dtype=torch.long)
x = torch.tensor([10, 20, 30, 40])
i = torch.tensor([0, 2, 2, 3])
r = r.scatter_add(0, i, x)

print(r)

