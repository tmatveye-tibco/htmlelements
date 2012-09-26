package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 24.09.12
 */
public class WrapsElementMatcher extends TypeSafeMatcher<WrapsElement> {
    private final Matcher<WebElement> matcher;

    public WrapsElementMatcher(Matcher<WebElement> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(WrapsElement element) {
        return matcher.matches(element.getWrappedElement());
    }

    @Override
    public void describeTo(Description description) {
        description.appendDescriptionOf(matcher);
    }

    @Override
    protected void describeMismatchSafely(WrapsElement element, Description mismatchDescription) {
        matcher.describeMismatch(element, mismatchDescription);
    }

    @Factory
    public static Matcher<WrapsElement> element(Matcher<WebElement> matcher) {
        return new WrapsElementMatcher(matcher);
    }
}
