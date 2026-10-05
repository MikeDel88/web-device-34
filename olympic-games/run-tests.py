from pathlib import Path
import argparse
import sys

def _exists(*names):
  return lambda p: any((p / n).exists() for n in names)

def _gradle_cmd(p):
  return ["./gradlew" if (p / "gradlew").exists() else "gradle", "clean", "test"]

def _angular_cmd(p):
  return ["npm", "test"]

PROJECT_TYPES = {
  "angular": {
    "detect": _exists("angular.json"),
    "cmd": _angular_cmd
  },
  "spring-gradle": {
    "detect": _exists("build.gradle", "build.gradle.kts"),
    "cmd": _gradle_cmd
  }
}

def detect(path):
  return [name for name, t in PROJECT_TYPES.items() if t["detect"](path)]

def found_type(path):
  found=detect(path)
  if not found:
    sys.exit(f"Aucun type de projet reconnu dans {path}.")
  if len(found) > 1:
    sys.exit(f"Plusieurs types détectés {found}.")
  return found[0]

def main():
  parser = argparse.ArgumentParser()
  parser.add_argument("--path", help="dossier du projet", type=str, default=".")
  args = parser.parse_args()

  path = Path(args.path).resolve()

  type= found_type(path)

  out_dir = path / "test-results"
  out_dir.mkdir(parents=True, exist_ok=True)
  for old in out_dir.glob("*.xml"):
    old.unlink()

  cmd=PROJECT_TYPES[type]["cmd"](path)
  print(f"[{type}] {' '.join(cmd)}")

if __name__ == "__main__":
  main()
