package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    public static void openProfilePage() {
        open("/profile");
    }

    public static void deleteItem() {
        $("#delete-record-undefined").click();
        $("#closeSmallModal-ok").click();
    }

    public static void checkDeleteBookWithUI() {
        $("#see-book-Git Pocket Guide").shouldNotBe(visible);
    }
}
