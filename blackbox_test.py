import os
import json
import re
import sys

if len(sys.argv) != 2:
  print("usage: ", sys.argv[0], " <testing-file.json>")
  exit(1)

testing_file = "src/test/resources/" + sys.argv[1]
print("Testing file ", testing_file)

with open(testing_file) as f:
  tests = json.load(f)

test_succeeds = 0
fail_compile = 0
fail_run = 0
fail_test = 0
for test in tests["tests"]:
  base_file = "target/" + test["name"]
  compile_out = base_file + "-compile.out"
  compile_err = base_file + "-compile.err"
  compile_command = test["setup"] + "> " + compile_out + " 2> " + compile_err
  if os.system(compile_command) != 0:
    fail_compile += 1
    print("Test \"", test["name"], "failed to compile (see " + compile_err + ")")
  else:
    run_in = base_file + "-run.in"
    with open(run_in, "w") as input_file:
      input_file.write(test["input"])
    run_out = base_file + "-run.out"
    run_err = base_file + "-run.err"
    program_test = test["run"] + " < " + run_in + " > " + run_out + " 2> " + run_err
    if os.system(program_test) != 0:
      fail_run = fail_run + 1
      print("Test \"", test["name"], "failed to run (see " + run_err + ")")
    else:
      with open(run_out) as f:
        output = f.read()
      if test["comparison"] == "included":
        if test["output"] in output:
          test_succeeds = test_succeeds + 1
        else:
          fail_test = fail_test + 1
          print("Test \"", test["name"], "failed to succeed (see " + run_out + ")")
      elif test["comparison"] == "regex":
        p = re.compile(test["output"])
        if p.match(output):
          test_succeeds = test_succeeds + 1
        else:
          fail_test = fail_test + 1
          print("Test \"", test["name"], "failed to succeed (see " + run_out + ")\tfail regex: " + test["output"])
      else:
        print("Error in the test file, comparison field unknown: " + test["comparison"] + " (use either \"included\" or \"regex\".")

print("name, succeeds, fail_compile, fail_run, fail_test")
print(f"{sys.argv[1]}, {test_succeeds}, {fail_compile}, {fail_run}, {fail_test}")
