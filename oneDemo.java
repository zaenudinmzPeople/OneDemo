Tests are specified using the top-level test() function, and test assertions are made using expect():

import "package:test/test.dart";

void main() {
  test("String.split() splits the string on the delimiter", () {
    var string = "foo,bar,baz";
    expect(string.split(","), equals(["foo", "bar", "baz"]));
  });

  test("String.trim() removes surrounding whitespace", () {
    var string = "  foo ";
    expect(string.trim(), equals("foo"));
  });
}
Tests can be grouped together using the group() function. Each group's description is added to the beginning of its test's descriptions.

import "package:test/test.dart";

void main() {
  group("String", () {
    test(".split() splits the string on the delimiter", () {
      var string = "foo,bar,baz";
      expect(string.split(","), equals(["foo", "bar", "baz"]));
    });

    test(".trim() removes surrounding whitespace", () {
      var string = "  foo ";
      expect(string.trim(), equals("foo"));
    });
  });

  group("int", () {
    test(".remainder() returns the remainder of division", () {
      expect(11.remainder(3), equals(2));
    });

    test(".toRadixString() returns a hex string", () {
      expect(11.toRadixString(16), equals("b"));
    });
  });
}
Any matchers from the matcher package can be used with expect() to do complex validations:

import "package:test/test.dart";

void main() {
  test(".split() splits the string on the delimiter", () {
    expect("foo,bar,baz", allOf([
      contains("foo"),
      isNot(startsWith("bar")),
      endsWith("baz")
    ]));
  });
}
You can use the setUp() and tearDown() functions to share code between tests. The setUp() callback will run before every test in a group or test suite, and tearDown() will run after. tearDown() will run even if a test fails, to ensure that it has a chance to clean up after itself.

import "package:test/test.dart";

void main() {
  HttpServer server;
  Uri url;
  setUp(() async {
    server = await HttpServer.bind('localhost', 0);
    url = Uri.parse("http://${server.address.host}:${server.port}");
  });

  tearDown(() async {
    await server.close(force: true);
    server = null;
    url = null;
  });

  // ...
}
Running Tests #
goodluck :)


