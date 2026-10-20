import torch

x = torch.tensor([10, 20, 30, 40])
i = torch.tensor([0, 2, 2, 3])
r = x[i]

print(r)

