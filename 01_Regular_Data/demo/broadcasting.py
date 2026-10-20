import torch

a = torch.tensor([[1, 2, 3], [4, 5, 6]])  # [2, 3]
b = torch.tensor([10, 20, 30])            # [3]
# b = torch.tensor([10, 20, 30, 40])        # [4]
print((a + b).shape)
print(a + b)


c = torch.tensor([100, 200]).unsqueeze(1)  # [2, 1]
print((a + c).shape)
print(a + c)


d = torch.tensor(1000)
# d = torch.tensor([1000])
print(d.shape)
print((a + d).shape)
print(a + d)


x = torch.tensor([1, 2, 3, 4])
print(x.shape)  # [4]
print(x.unsqueeze(0).shape)  # [1, 4]
print(x.unsqueeze(1).shape)  # [4, 1]
print((x.unsqueeze(1) + b).shape)
print(x.unsqueeze(1) + b)    # [4, 3]

