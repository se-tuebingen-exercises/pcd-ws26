import torch

a = torch.tensor([[1.0, 2.0], [3.0, 4.0]])
b = torch.tensor([[5.0, 6.0], [7.0, 8.0]])

print((a.unsqueeze(2) * b.unsqueeze(0)).sum(dim=1))
print(a @ b)

input = torch.randn(1, 8, 16)
weights = torch.randn(4, 16, 12)
print((input @ weights).shape)

