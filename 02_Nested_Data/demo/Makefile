PROGRAMS := $(basename $(wildcard *.fut))
RUNS := $(addprefix run_,$(PROGRAMS))

.PHONY: all clean $(RUNS)

all: $(PROGRAMS)

%: %.fut
	futhark multicore $<

$(RUNS): run_%: %
	./$< < $<.input

clean:
	rm -f $(PROGRAMS) $(addsuffix .c,$(PROGRAMS)) $(addsuffix .h,$(PROGRAMS))

