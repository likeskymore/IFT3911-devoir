import Link from "next/link";

const tripTypes = [
    {
        id: "local",
        title: "Local Trip",
        description: "Short-distance trips inside a city or region — cheap and fast.",
    },
    {
        id: "intercity",
        title: "Intercity Trip",
        description: "Medium-distance trips between cities — great for commuters.",
    },
    {
        id: "international",
        title: "International Trip",
        description: "Long-distance trips crossing countries — plan ahead.",
    },
];

const HomePage = () => {
    return (
        <main className="min-h-screen flex flex-col">
            <header className="bg-linear-to-r from-sky-600 to-indigo-600 text-white py-12">
                <div className="container mx-auto px-6">
                    <h1 className="text-4xl font-bold">Trip Ticket System</h1>
                    <p className="mt-3 max-w-2xl text-lg opacity-90">
                        Create and manage tickets for three trip types: Local, Intercity and
                        International. Start by choosing a trip type below.
                    </p>
                </div>
            </header>

            <section className="container mx-auto px-6 py-12 flex-1">
                <div className="grid gap-6 grid-cols-1 sm:grid-cols-2 lg:grid-cols-3">
                    {tripTypes.map((t) => (
                        <article
                            key={t.id}
                            className="rounded-xl border p-6 shadow-sm hover:shadow-md transition"
                        >
                            <h2 className="text-2xl font-semibold">{t.title}</h2>
                            <p className="mt-2 text-sm text-muted-foreground">{t.description}</p>

                            <div className="mt-6 flex gap-3">
                                <Link
                                    href={`/?create=${t.id}`}
                                    className="inline-flex items-center gap-2 rounded-md bg-primary px-4 py-2 text-white"
                                >
                                    Create Ticket
                                </Link>

                                <Link
                                    href={`/trips?type=${t.id}`}
                                    className="inline-flex items-center gap-2 rounded-md border px-4 py-2"
                                >
                                    View Samples
                                </Link>
                            </div>
                        </article>
                    ))}
                </div>
            </section>
        </main>
    );
};

export default HomePage;