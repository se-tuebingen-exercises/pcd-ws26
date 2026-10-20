import torch

def cnn_forward(image, kernel, bias1, weights, bias2):
    ...

image = torch.randn(16, 16)
kernel = torch.randn(3, 3)
bias1 = torch.randn(1).item()
weights = torch.randn(10, 196)
bias2 = torch.randn(10)

logits = cnn_forward(image, kernel, bias1, weights, bias2)

print("Logits:")
print(logits)
print(f"\nPredicted digit: {torch.argmax(logits).item()}")
