import { APP_NAME } from "@/lib/constants";
import Link from "next/link";
import Image from "next/image";

const Header = () => {
  return (<header className="w-full border-b">
    <div className="wraper flex-between px-4 md:px-8 lg:px-16">
      <div className="flex-start">
        <Link href="/" className="flex-start ml-4">
          <Image
            src="/images/logo.jpg"
            alt={`${APP_NAME} logo`}
            height={48}
            width={48}
            priority={true}
            className="mt-1 mb-1"
          />
          <span className="hidden lg:block font-bold text-2xl ml-3">
            {APP_NAME}
          </span>
        </Link>
      </div>
    </div>
  </header>);
}

export default Header;