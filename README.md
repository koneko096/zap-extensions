[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/zaproxy/zap-extensions.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/zaproxy/zap-extensions/context:java)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=zaproxy_zap-extensions&metric=alert_status)](https://sonarcloud.io/dashboard?id=zaproxy_zap-extensions)

This project contains add-ons for the [OWASP Zed Attack Proxy](https://github.com/zaproxy/zaproxy) (ZAP).

If you are using the latest version of ZAP then you can browse and download add-ons from within ZAP by clicking on this button in the toolbar:

![Image](https://github.com/zaproxy/zap-extensions/wiki/images/zap-screenshot-browse-addons.png)

You can also import add-ons you have downloaded manually from https://github.com/zaproxy/zap-extensions/releases via the "File / Load Add-on File..." menu option.

Please see the [wiki](https://github.com/zaproxy/zap-extensions/wiki) for more details.

## Building

The add-ons are built with [Gradle], each add-on has its own project which is located under the `addOns` project/directory.

To build all add-ons, simply run:

    ./gradlew build

in the main directory of the project, the add-ons will be placed in the directory `build/zapAddOn/bin/` of each project.

To build an add-on individually run:

    ./gradlew :addOns:<name>:build

replacing `<name>` with the name of the add-on (e.g. `reveal`).

[Gradle]: https://gradle.org/
